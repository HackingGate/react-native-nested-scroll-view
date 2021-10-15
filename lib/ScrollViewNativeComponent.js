/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 *
 * @format
 * @flow
 */

 'use strict';

 const registerGeneratedViewConfig = require('react-native/Libraries/Utilities/registerGeneratedViewConfig');
 const requireNativeComponent = require('react-native/Libraries/ReactNative/requireNativeComponent');
 import ScrollViewViewConfig from 'react-native/Libraries/Components/ScrollView/ScrollViewViewConfig';
 
 import type {
   ScrollViewNativeProps,
   ScrollViewNativeComponentType,
 } from 'react-native/Libraries/Components/ScrollView/ScrollViewNativeComponentType';

 
 let ScrollViewNativeComponent;
 if (global.RN$Bridgeless) {
   registerGeneratedViewConfig('RCTOriginalScrollView', ScrollViewViewConfig);
   ScrollViewNativeComponent = 'RCTOriginalScrollView';
 } else {
   ScrollViewNativeComponent = requireNativeComponent<ScrollViewNativeProps>(
     'RCTOriginalScrollView',
   );
 }
 
 export default ((ScrollViewNativeComponent: any): ScrollViewNativeComponentType);
 