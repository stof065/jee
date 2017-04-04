package org.core.ejb;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.core.ejb.commun.AbstractDao;
import org.core.ejb.commun.ElasticSearchAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

public class ElasticSearchInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchInterceptor.class);

	ObjectMapper mapper = new ObjectMapper();

	JestClientFactory factory = new JestClientFactory();

	@PostConstruct
	public void init() {
		factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build());
	}

	@AroundInvoke
	public Object elastciSearchInterceptor(InvocationContext ctx) {

		Class<?> type = getGenericType(ctx);
		if (type.getAnnotation(ElasticSearchAspect.class) != null) {
			// RUN ELASTICSEARCH
			return runElasticSearch(ctx);
		} else {
			try {
				ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	private Object runElasticSearch(InvocationContext ctx) {
		LOG.info("RUN ELASTICSEARCH ... ");
		JestClient client = factory.getObject();
		boolean indexExists;
		try {
			indexExists = client.execute(new IndicesExists.Builder("mkyong").build()).isSucceeded();
			if (indexExists) {
				client.execute(new DeleteIndex.Builder("jug").build());
			}
			client.execute(new CreateIndex.Builder("jug").build());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

	private Class<?> getGenericType(InvocationContext ctx) {
		AbstractDao<?, ?> target = (AbstractDao<?, ?>) ctx.getTarget();
		return target.getType();
	}

}
