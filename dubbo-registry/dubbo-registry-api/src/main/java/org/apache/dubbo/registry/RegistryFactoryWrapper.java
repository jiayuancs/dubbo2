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

package org.apache.dubbo.registry;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Collections;

/**
 * 装饰器模式
 */
public class RegistryFactoryWrapper implements RegistryFactory {
    private RegistryFactory registryFactory;

    /**
     * 包含一个参数为扩展点类型的构造函数，表明该类是一个 Wrapper 类，
     * ExtensionLoader 在加载扩展类对象时，会自动用该类包装扩展类对象
     * @param registryFactory
     */
    public RegistryFactoryWrapper(RegistryFactory registryFactory) {
        this.registryFactory = registryFactory;
    }

    /**
     * 为 registry 添加了监听器，
     * 执行 register、unregister、subscribe、unsubscribe 等操作时会回调 RegistryServiceListener 扩展点实现类的中的方法
     * @param url Registry address, is not allowed to be empty
     * @return
     */
    @Override
    public Registry getRegistry(URL url) {
        //
        return new ListenerRegistryWrapper(registryFactory.getRegistry(url),
                Collections.unmodifiableList(ExtensionLoader.getExtensionLoader(RegistryServiceListener.class)
                        .getActivateExtension(url, "registry.listeners")));
    }
}
