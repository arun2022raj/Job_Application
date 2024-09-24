package com.embarkx.gate_way;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {


	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}


	/*@Bean
	@Lazy(false)
	public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> apis(RouteDefinitionLocator locator,
																  SwaggerUiConfigParameters swaggerUiConfigParameters) {
		Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();

		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*ms")).forEach(routeDefinition -> {
			String name = routeDefinition.getId().replaceAll("ms", "");
			AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(name, "/ilove" + name, null);
			urls.add(swaggerUrl);
			System.out.println(urls);
		});
		swaggerUiConfigParameters.setUrls(urls);
		return urls;
	}*/
	/*@Bean
	@Lazy(false)
	public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> apis(RouteDefinitionLocator locator,
																  SwaggerUiConfigParameters swaggerUiConfigParameters) {
		Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*ms")).forEach(routeDefinition -> {
			String name = routeDefinition.getId().replaceAll("ms", "");
			AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(name, "/" + name, null);
			urls.add(swaggerUrl);
			System.out.println(swaggerUrl);
		});

		return urls;
	}*/

}
