/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ayvytr.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.internal.Preconditions;

import io.reactivex.annotations.NonNull;

/**
 * ================================================
 * 使用此类操作 RxLifecycle 的特性
 * <p>
 * Created by JessYan on 26/08/2017 17:52
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

public class RxLifecycleUtils {

    private RxLifecycleUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 绑定 Activity 的指定生命周期
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final IView view,
                                                             final ActivityEvent event) {
        Preconditions.checkNotNull(view, "view == null");
        if(view instanceof RxAppCompatActivity) {
            return RxLifecycle.bindUntilEvent(((RxAppCompatActivity) view).lifecycle(), event);
        }
        throw new IllegalArgumentException("view isn't ActivityLifecycleable");
    }

    /**
     * 绑定 Fragment 的指定生命周期
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull IView view, @NonNull FragmentEvent event) {
        Preconditions.checkNotNull(view, "view == null");
        if(view instanceof RxFragment) {
            return RxLifecycle.bindUntilEvent(((RxFragment) view).lifecycle(), event);
        }
        throw new IllegalArgumentException("view isn't FragmentLifecycleable");
    }

    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull IView view) {
        Preconditions.checkNotNull(view, "lifecycleable == null");
        if(view instanceof RxAppCompatActivity) {
            return RxLifecycleAndroid.bindActivity(((RxAppCompatActivity) view).lifecycle());
        } else if(view instanceof RxFragment) {
            return RxLifecycleAndroid.bindFragment(((RxFragment) view).lifecycle());
        } else {
            throw new IllegalArgumentException("Lifecycle not match");
        }
    }
}
