// From https://medium.com/widgetlabs-engineering/scrollable-nestedscrollviews-inside-recyclerview-ca65050d828a
// Replaced RecyclerView with ScrollView
package com.rnoriginalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ScrollView;
// import java.util.HashMap;
// import kotlin.Metadata;
// import kotlin.jvm.internal.Intrinsics;
// import org.jetbrains.annotations.NotNull;
// import org.jetbrains.annotations.Nullable;

// @Metadata(
//    mv = {1, 1, 18},
//    bv = {1, 0, 3},
//    k = 1,
//    d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J0\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0016J \u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010 \u001a\u00020\tH\u0016J \u0010!\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\tH\u0016J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\fH\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"},
//    d2 = {"Lcom/rnoriginalscrollview/IOSLikeScrollView;", "Landroid/widget/ScrollView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "nestedScrollTarget", "Landroid/view/View;", "nestedScrollTargetIsBeingDragged", "", "nestedScrollTargetWasUnableToScroll", "shouldIntercept", "skipsTouchInterception", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "onInterceptTouchEvent", "e", "onNestedScroll", "", "target", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "onNestedScrollAccepted", "child", "axes", "onStartNestedScroll", "nestedScrollAxes", "onStopNestedScroll", "expedition-nestedscrollview.app"}
// )
public class IOSLikeScrollView extends ScrollView {
   private View nestedScrollTarget;
   private boolean nestedScrollTargetIsBeingDragged;
   private boolean nestedScrollTargetWasUnableToScroll;
   private boolean skipsTouchInterception;
   private boolean shouldIntercept;
//    private HashMap _$_findViewCache;

//    public boolean dispatchTouchEvent(@NotNull MotionEvent ev) {
   public boolean dispatchTouchEvent(MotionEvent ev) {
    //   Intrinsics.checkParameterIsNotNull(ev, "ev");
      boolean temporarilySkipsInterception = this.nestedScrollTarget != null;
      if (temporarilySkipsInterception) {
         this.skipsTouchInterception = true;
      }

      boolean handled = super.dispatchTouchEvent(ev);
      if (temporarilySkipsInterception) {
         this.skipsTouchInterception = false;
         if (!handled || this.nestedScrollTargetWasUnableToScroll) {
            this.shouldIntercept = true;
            handled = super.dispatchTouchEvent(ev);
         }
      }

      return handled;
   }

//    public boolean onInterceptTouchEvent(@NotNull MotionEvent e) {
   public boolean onInterceptTouchEvent(MotionEvent e) {
    //   Intrinsics.checkParameterIsNotNull(e, "e");
      if (this.shouldIntercept) {
         this.shouldIntercept = false;
         return true;
      } else {
         return !this.skipsTouchInterception && super.onInterceptTouchEvent(e);
      }
   }

//    public void onNestedScroll(@NotNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
   public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    //   Intrinsics.checkParameterIsNotNull(target, "target");
      if (target == this.nestedScrollTarget && !this.nestedScrollTargetIsBeingDragged) {
         if (dyConsumed != 0) {
            this.nestedScrollTargetIsBeingDragged = true;
            this.nestedScrollTargetWasUnableToScroll = false;
         } else if (dyConsumed == 0 && dyUnconsumed != 0) {
            this.nestedScrollTargetWasUnableToScroll = true;
            ViewParent var10000 = target.getParent();
            if (var10000 != null) {
               var10000.requestDisallowInterceptTouchEvent(false);
            }
         }
      }

   }

//    public void onNestedScrollAccepted(@NotNull View child, @NotNull View target, int axes) {
   public void onNestedScrollAccepted(View child, View target, int axes) {
    //   Intrinsics.checkParameterIsNotNull(child, "child");
    //   Intrinsics.checkParameterIsNotNull(target, "target");
      if ((axes & 2) != 0) {
         this.nestedScrollTarget = target;
         this.nestedScrollTargetIsBeingDragged = false;
         this.nestedScrollTargetWasUnableToScroll = false;
      }

      super.onNestedScrollAccepted(child, target, axes);
   }

//    public boolean onStartNestedScroll(@NotNull View child, @NotNull View target, int nestedScrollAxes) {
   public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    //   Intrinsics.checkParameterIsNotNull(child, "child");
    //   Intrinsics.checkParameterIsNotNull(target, "target");
      return (nestedScrollAxes & 2) != 0;
   }

//    public void onStopNestedScroll(@NotNull View child) {
   public void onStopNestedScroll(View child) {
    //   Intrinsics.checkParameterIsNotNull(child, "child");
      this.nestedScrollTarget = (View)null;
      this.nestedScrollTargetIsBeingDragged = false;
      this.nestedScrollTargetWasUnableToScroll = false;
   }

//    public IOSLikeScrollView(@NotNull Context context) {
   public IOSLikeScrollView(Context context) {
    //   Intrinsics.checkParameterIsNotNull(context, "context");
      super(context);
   }

//    public IOSLikeScrollView(@NotNull Context context, @Nullable AttributeSet attrs) {
   public IOSLikeScrollView(Context context, AttributeSet attrs) {
    //   Intrinsics.checkParameterIsNotNull(context, "context");
      super(context, attrs);
   }

//    public IOSLikeScrollView(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
   public IOSLikeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    //   Intrinsics.checkParameterIsNotNull(context, "context");
      super(context, attrs, defStyleAttr);
   }

//    public View _$_findCachedViewById(int var1) {
//       if (this._$_findViewCache == null) {
//          this._$_findViewCache = new HashMap();
//       }

//       View var2 = (View)this._$_findViewCache.get(var1);
//       if (var2 == null) {
//          var2 = this.findViewById(var1);
//          this._$_findViewCache.put(var1, var2);
//       }

//       return var2;
//    }

//    public void _$_clearFindViewByIdCache() {
//       if (this._$_findViewCache != null) {
//          this._$_findViewCache.clear();
//       }

//    }
}
