package com.nicico.committee.client.hrm;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class HrmErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            String errorBody = null;
            if (response.body() != null) {
                errorBody = response.body().string();
            }
            throw new HrmApiException(response.code(), errorBody);
        }
        return response;
    }
}
