package net.bodz.bas.bean.api;

import java.awt.Image;

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
public interface IBeanInfo {

    int ICON_COLOR_16x16 = 1;

    int ICON_COLOR_32x32 = 2;

    int ICON_MONO_16x16 = 3;

    int ICON_MONO_32x32 = 4;

    public IBeanDescriptor getBeanDescriptor();

    public IPropertyDescriptor[] getPropertyDescriptors();

    public IMethodDescriptor[] getMethodDescriptors();

    public IEventSetDescriptor[] getEventSetDescriptors();

    public IBeanInfo[] getAdditionalBeanInfo();

    public Image getIcon(int iconKind);

    public int getDefaultPropertyIndex();

    public int getDefaultEventIndex();
}
