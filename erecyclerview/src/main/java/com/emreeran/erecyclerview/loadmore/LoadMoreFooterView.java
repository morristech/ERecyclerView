package com.emreeran.erecyclerview.loadmore;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.emreeran.erecyclerview.R;

/**
 * Base class for load more views, all views used as load more items must extend this class
 * Created by Emre Eran on 11/12/15.
 */
public class LoadMoreFooterView extends FrameLayout {

    private OnLoadMoreListener mOnLoadMoreListener;
    private LoadMoreViewStateListener mLoadMoreViewStateListener;

    public LoadMoreFooterView(Context context) {
        super(context);
    }

    public LoadMoreFooterView createView(int resourceId, @Nullable LoadMoreViewStateListener listener) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId, this, false);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        setLayoutTransition(new LayoutTransition());
        setLayoutParams(lp);
        addView(view);
        setVisibility(INVISIBLE);
        mLoadMoreViewStateListener = listener;

        if (mLoadMoreViewStateListener != null) {
            mLoadMoreViewStateListener.onViewCreated(view);
        }

        return this;
    }

    public void onLoad() {
        setVisibility(VISIBLE);
        mOnLoadMoreListener.onLoadMore();
        if (mLoadMoreViewStateListener != null) {
            mLoadMoreViewStateListener.onLoad();
        }
    }

    public void onComplete() {
        setVisibility(INVISIBLE);
        if(mLoadMoreViewStateListener != null) {
            mLoadMoreViewStateListener.onComplete();
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }
}
