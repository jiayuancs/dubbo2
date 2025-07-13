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
import org.apache.dubbo.common.extension.SPI;

/**
 * RegistryServiceListener 扩展点用于实现当向注册中心执行 register、unregister、subscribe、unsubscribe 等操作时的回调方法。
 *
 * Dubbo 没有提供该扩展点的实现，该扩展点应由用户根据实际需要实现
 *
 * @see org.apache.dubbo.registry.ListenerRegistryWrapper
 * @see org.apache.dubbo.registry.RegistryFactoryWrapper
 */
@SPI
public interface RegistryServiceListener {
    default void onRegister(URL url) {

    }


    default void onUnregister(URL url) {

    }

    default void onSubscribe(URL url) {

    }

    default void onUnsubscribe(URL url) {

    }
}
