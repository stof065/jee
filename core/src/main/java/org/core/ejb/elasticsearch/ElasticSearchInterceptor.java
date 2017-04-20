package org.core.ejb.elasticsearch;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.core.ejb.commun.AbstractDao;
import org.core.ejb.commun.ElasticSearchAspect;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

public class ElasticSearchInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchInterceptor.class);

	ObjectMapper mapper = new ObjectMapper();

	JestClientFactory factory = new JestClientFactory();

	@PostConstruct
	public void init() {
		LOG.info("instance :" + this);
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
			return	ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
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
				search(client);
			}
			client.execute(new CreateIndex.Builder("jug").build());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	private Class<?> getGenericType(InvocationContext ctx) {
		AbstractDao<?, ?> target = (AbstractDao<?, ?>) ctx.getTarget();
		return target.getType();
	}

	public void search(JestClient client) {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("title", "Apache"));

		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("mkyong").addType("books").build();
		System.out.println(searchSourceBuilder.toString());
		try {
			JestResult result = client.execute(search);
			System.out.println(result.getSourceAsString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
