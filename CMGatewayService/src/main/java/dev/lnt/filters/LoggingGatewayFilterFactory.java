package dev.lnt.filters;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Component
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config>{

	final Logger logger = LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);
	
	public LoggingGatewayFilterFactory() {
		super(Config.class);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Config{
		private String baseMessage;
		private boolean preLogger;
		private boolean postLogger;
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			if(config.isPreLogger()) {
				logger.info("Pre GatewayFilter logging: " + config.getBaseMessage());
			}
			return chain.filter(exchange)
					.then(Mono.fromRunnable(() -> {
						if(config.isPostLogger()) {
							logger.info("Post GatewayFilter logging: " + config.getBaseMessage());
						}
					}));
		};
	}
	
	@Override
	public List<String> shortcutFieldOrder(){
		return Arrays.asList("baseMessage", "preLogger", "postLogger");
	}
	
}
