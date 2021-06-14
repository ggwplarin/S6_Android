package com.example.mvvmsqllite

import android.content.Context

object mainactivitycontext
{
    private var context: Context? = null;

    fun setContext(context: Context)
    {
        this.context = context
    }

    fun getContext(): Context? {
        return this.context
    }
}