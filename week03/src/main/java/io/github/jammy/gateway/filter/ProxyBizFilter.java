package io.github.jammy.gateway.filter;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class ProxyBizFilter implements HttpRequestFilter {

	@Override
	public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		String uri = fullRequest.uri();
		if (!uri.startsWith("/test")) {
			throw new RuntimeException("Don't support the uri: " + uri);
		}
		
		HttpHeaders headers = fullRequest.headers();
        if (headers == null) {
            headers = new DefaultHttpHeaders();
        }
        headers.add("proxy-tag", this.getClass().getSimpleName());
	}

}
