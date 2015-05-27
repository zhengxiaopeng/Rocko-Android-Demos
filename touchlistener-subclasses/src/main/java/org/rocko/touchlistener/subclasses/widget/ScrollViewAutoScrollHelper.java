/*
 * Copyright 2015 Rocko(zhengxiaopeng).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rocko.touchlistener.subclasses.widget;

import android.support.v4.widget.AutoScrollHelper;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/4/26.
 */
public class ScrollViewAutoScrollHelper extends AutoScrollHelper {
    protected ScrollView mTarger;

    public ScrollViewAutoScrollHelper(ScrollView target) {
        super(target);
        this.mTarger = target;
    }

    @Override
    public void scrollTargetBy(int deltaX, int deltaY) {
        mTarger.smoothScrollBy(deltaX, deltaY);
    }

    @Override
    public boolean canTargetScrollHorizontally(int direction) {
        return mTarger.canScrollHorizontally(direction);
    }

    @Override
    public boolean canTargetScrollVertically(int direction) {
        return mTarger.canScrollVertically(direction);
    }
}
