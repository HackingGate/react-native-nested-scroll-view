package com.rnoriginalscrollview;

import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;

public class ReactHorizontalOriginalScrollContainerViewManager
    extends ReactHorizontalScrollContainerViewManager {

  protected static final String REACT_CLASS = "AndroidHorizontalOriginalScrollContentView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }
}
