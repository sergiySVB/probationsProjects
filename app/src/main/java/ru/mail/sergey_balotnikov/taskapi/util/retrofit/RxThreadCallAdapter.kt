package ru.mail.sergey_balotnikov.taskapi.util.retrofit

import retrofit2.CallAdapter
import retrofit2.Retrofit
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type


class RxThreadCallAdapter(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : CallAdapter.Factory() {

    internal var rxFactory = RxJava2CallAdapterFactory.create()

    internal inner class ThreadCallAdapter(var delegateAdapter: CallAdapter<Single<*>, *>) :
        CallAdapter<Single<*>, Any> {

        override fun responseType(): Type {
            return delegateAdapter.responseType()
        }


        override fun adapt(call: Call<Single<*>>): Any {
            return (delegateAdapter.adapt(call) as Single<*>).subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler)
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val callAdapter = rxFactory.get(returnType, annotations, retrofit) as CallAdapter<Single<*>, *>
        return if (callAdapter != null) ThreadCallAdapter(callAdapter) else null
    }
}