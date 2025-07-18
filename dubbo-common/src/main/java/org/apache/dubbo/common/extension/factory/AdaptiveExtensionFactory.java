/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.common.extension.factory;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionFactory;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AdaptiveExtensionFactory
 * AdaptiveExtensionFactory 不实现任何具体的功能，
 * 而是用来适配 ExtensionFactory 的 SpiExtensionFactory 和 SpringExtensionFactory 这两种实现。
 * AdaptiveExtensionFactory 会根据运行时的一些状态来选择具体调用 ExtensionFactory 的哪个实现。
 */
@Adaptive
public class AdaptiveExtensionFactory implements ExtensionFactory {

    /**
     * 当前 dubbo 支持的所有 ExtensionFactory 扩展实现类对象
     */
    private final List<ExtensionFactory> factories;

    public AdaptiveExtensionFactory() {
        ExtensionLoader<ExtensionFactory> loader = ExtensionLoader.getExtensionLoader(ExtensionFactory.class);
        List<ExtensionFactory> list = new ArrayList<ExtensionFactory>();
        // loader.getSupportedExtensions() 获取了所有 ExtensionFactory 的扩展名集合
        for (String name : loader.getSupportedExtensions()) {
            // 根据扩展名获取扩展类实现对象
            list.add(loader.getExtension(name));
        }
        factories = Collections.unmodifiableList(list);
    }

    /**
     * 根据扩展点和扩展名获取扩展实现类对象
     * @param type object type. 扩展点，例如 org.apache.dubbo.rpc.Protocol
     * @param name object name. 扩展名 dubbo
     * @return
     * @param <T>
     */
    @Override
    public <T> T getExtension(Class<T> type, String name) {
        for (ExtensionFactory factory : factories) {
            T extension = factory.getExtension(type, name);
            if (extension != null) {
                return extension;
            }
        }
        return null;
    }

}
