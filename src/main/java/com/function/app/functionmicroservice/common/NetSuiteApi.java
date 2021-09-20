package com.function.app.functionmicroservice.common;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.builder.api.OAuth1SignatureType;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.services.SignatureService;

	public class NetSuiteApi extends DefaultApi10a {

	    private static class InstanceHolder {
	        private static final NetSuiteApi INSTANCE = new NetSuiteApi();
	    }

	    public static NetSuiteApi instance() {
	        return InstanceHolder.INSTANCE;
	    }

	    @Override
	    public String getAccessTokenEndpoint() {
	        return null;
	    }

	    @Override
	    public String getRequestTokenEndpoint() {
	        return null;
	    }

	    @Override
	    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
	        return null;
	    }

	    @Override
	    protected String getAuthorizationBaseUrl() {
	        return null;
	    }
	    
	    @Override
	    public SignatureService getSignatureService() {
	        return new HMACSha256SignatureService();
	    }

	@Override
	public OAuth1SignatureType getSignatureType() {
	    return OAuth1SignatureType.HEADER;
	}	

}
