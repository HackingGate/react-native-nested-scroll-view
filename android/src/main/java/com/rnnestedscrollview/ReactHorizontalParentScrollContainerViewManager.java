package com.rnnestedscrollview;

import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;

public class ReactHorizontalParentScrollContainerViewManager
    extends ReactHorizontalScrollContainerViewManager {

  protected static final String REACT_CLASS = "AndroidHorizontalParentScrollContentView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }
}
