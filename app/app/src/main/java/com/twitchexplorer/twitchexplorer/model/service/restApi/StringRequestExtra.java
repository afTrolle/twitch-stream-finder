package com.twitchexplorer.twitchexplorer.model.service.restApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class StringRequestExtra extends StringRequest {

    private final Map<String, String> params;

    public StringRequestExtra(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> params) {
        super(method, url, listener, errorListener);
        this.params = params;
    }

    public StringRequestExtra(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> params) {
        super(url, listener, errorListener);
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
