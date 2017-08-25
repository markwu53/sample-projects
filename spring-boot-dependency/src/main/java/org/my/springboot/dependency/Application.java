package org.my.springboot.dependency;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        Arrays.stream(context.getBeanDefinitionNames())
            .filter(s -> !s.startsWith("org."))
            .filter(s -> !s.startsWith("spring."))
            .map(s -> new String[] { s, context.getBean(s).getClass().getName() })
            //.map(context::getBean)
            //.map(Object::getClass)
            //.map(Class::getName)
            //.forEach(System.out::println);
            //.forEach(pair -> System.out.println(String.format("%s: %s", pair)));
            .forEach(pair -> System.out.println(String.format("%s: %s", pair[0], pair[1])));
    }

    /*
application: org.my.springboot.dependency.Application$$EnhancerBySpringCGLIB$$6ead5b58
profileController: org.springframework.data.rest.webmvc.ProfileController
repositoryEntityController: org.springframework.data.rest.webmvc.RepositoryEntityController
repositoryPropertyReferenceController: org.springframework.data.rest.webmvc.RepositoryPropertyReferenceController
repositorySchemaController: org.springframework.data.rest.webmvc.RepositorySchemaController
alpsController: org.springframework.data.rest.webmvc.alps.AlpsController
repositoryController: org.springframework.data.rest.webmvc.RepositoryController
repositorySearchController: org.springframework.data.rest.webmvc.RepositorySearchController
propertySourcesPlaceholderConfigurer: org.springframework.context.support.PropertySourcesPlaceholderConfigurer
standardJacksonObjectMapperBuilderCustomizer: org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$StandardJackson2ObjectMapperBuilderCustomizer
jacksonObjectMapperBuilder: org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
jacksonObjectMapper: com.fasterxml.jackson.databind.ObjectMapper
jsonComponentModule: org.springframework.boot.jackson.JsonComponentModule
websocketContainerCustomizer: org.springframework.boot.autoconfigure.websocket.JettyWebSocketContainerCustomizer
jettyEmbeddedServletContainerFactory: org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory
embeddedServletContainerCustomizerBeanPostProcessor: org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor
errorPageRegistrarBeanPostProcessor: org.springframework.boot.web.servlet.ErrorPageRegistrarBeanPostProcessor
dispatcherServlet: org.springframework.web.servlet.DispatcherServlet
dispatcherServletRegistration: org.springframework.boot.web.servlet.ServletRegistrationBean
error: org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$SpelView
beanNameViewResolver: org.springframework.web.servlet.view.BeanNameViewResolver
conventionErrorViewResolver: org.springframework.boot.autoconfigure.web.DefaultErrorViewResolver
errorAttributes: org.springframework.boot.autoconfigure.web.DefaultErrorAttributes
basicErrorController: org.springframework.boot.autoconfigure.web.BasicErrorController
errorPageCustomizer: org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$ErrorPageCustomizer
preserveErrorControllerTargetClassPostProcessor: org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$PreserveErrorControllerTargetClassPostProcessor
requestMappingHandlerAdapter: org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
requestMappingHandlerMapping: org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
mvcPathMatcher: org.springframework.util.AntPathMatcher
mvcUrlPathHelper: org.springframework.web.util.UrlPathHelper
mvcContentNegotiationManager: org.springframework.web.accept.ContentNegotiationManager
viewControllerHandlerMapping: org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport$EmptyHandlerMapping
beanNameHandlerMapping: org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
resourceHandlerMapping: org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
mvcResourceUrlProvider: org.springframework.web.servlet.resource.ResourceUrlProvider
defaultServletHandlerMapping: org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport$EmptyHandlerMapping
mvcConversionService: org.springframework.format.support.DefaultFormattingConversionService
mvcValidator: org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean
mvcUriComponentsContributor: org.springframework.web.method.support.CompositeUriComponentsContributor
httpRequestHandlerAdapter: org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
simpleControllerHandlerAdapter: org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
handlerExceptionResolver: org.springframework.web.servlet.handler.HandlerExceptionResolverComposite
mvcViewResolver: org.springframework.web.servlet.view.ViewResolverComposite
faviconHandlerMapping: org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
faviconRequestHandler: org.springframework.web.servlet.resource.ResourceHttpRequestHandler
defaultViewResolver: org.springframework.web.servlet.view.InternalResourceViewResolver
viewResolver: org.springframework.web.servlet.view.ContentNegotiatingViewResolver
welcomePageHandlerMapping: org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WelcomePageHandlerMapping
requestContextFilter: org.springframework.boot.web.filter.OrderedRequestContextFilter
hiddenHttpMethodFilter: org.springframework.boot.web.filter.OrderedHiddenHttpMethodFilter
httpPutFormContentFilter: org.springframework.boot.web.filter.OrderedHttpPutFormContentFilter
mbeanExporter: org.springframework.jmx.export.annotation.AnnotationMBeanExporter
objectNamingStrategy: org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy
mbeanServer: com.sun.jmx.mbeanserver.JmxMBeanServer
dataSource: org.apache.tomcat.jdbc.pool.DataSource
tomcatPoolDataSourceMetadataProvider: org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration$TomcatDataSourcePoolMetadataProviderConfiguration$1
dataSourceInitializer: org.springframework.boot.autoconfigure.jdbc.DataSourceInitializer
dataSourceInitializerPostProcessor: org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerPostProcessor
openEntityManagerInViewInterceptor: org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor
transactionManager: org.springframework.orm.jpa.JpaTransactionManager
jpaVendorAdapter: org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
entityManagerFactoryBuilder: org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
entityManagerFactory: com.sun.proxy.$Proxy70
dataSourceInitializedPublisher: org.springframework.boot.autoconfigure.orm.jpa.DataSourceInitializedPublisher
persistenceExceptionTranslationPostProcessor: org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
validator: org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
methodValidationPostProcessor: org.springframework.validation.beanvalidation.MethodValidationPostProcessor
emBeanDefinitionRegistrarPostProcessor: org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor
jpaMappingContext: org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
jpaContext: org.springframework.data.jpa.repository.support.DefaultJpaContext
transactionAttributeSource: org.springframework.transaction.annotation.AnnotationTransactionAttributeSource
transactionInterceptor: org.springframework.transaction.interceptor.TransactionInterceptor
transactionTemplate: org.springframework.transaction.support.TransactionTemplate
platformTransactionManagerCustomizers: org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers
gson: com.google.gson.Gson
stringHttpMessageConverter: org.springframework.http.converter.StringHttpMessageConverter
mappingJackson2HttpMessageConverter: org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
messageConverters: org.springframework.boot.autoconfigure.web.HttpMessageConverters
linkRelationMessageSource: org.springframework.context.support.MessageSourceAccessor
jacksonGeoModule: org.springframework.data.geo.GeoModule
repositories: org.springframework.data.repository.support.Repositories
repositoryRelProvider: org.springframework.data.rest.core.support.RepositoryRelProvider
persistentEntities: org.springframework.data.mapping.context.PersistentEntities
defaultConversionService: org.springframework.format.support.DefaultFormattingConversionService
validatingRepositoryEventListener: org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
jpaHelper: org.springframework.data.rest.webmvc.support.JpaHelper
config: org.springframework.data.rest.core.config.RepositoryRestConfiguration
projectionDefinitionRegistrar: org.springframework.data.rest.webmvc.config.ProjectionDefinitionRegistar
metadataConfiguration: org.springframework.data.rest.core.config.MetadataConfiguration
baseUri: org.springframework.data.rest.webmvc.BaseUri
annotatedEventHandlerInvoker: org.springframework.data.rest.core.event.AnnotatedEventHandlerInvoker
domainObjectMerger: org.springframework.data.rest.core.support.DomainObjectMerger
serverHttpRequestMethodArgumentResolver: org.springframework.data.rest.webmvc.ServerHttpRequestMethodArgumentResolver
repoRequestArgumentResolver: org.springframework.data.rest.webmvc.config.RootResourceInformationHandlerMethodArgumentResolver
resourceMetadataHandlerMethodArgumentResolver: org.springframework.data.rest.webmvc.config.ResourceMetadataHandlerMethodArgumentResolver
backendIdHandlerMethodArgumentResolver: org.springframework.data.rest.webmvc.support.BackendIdHandlerMethodArgumentResolver
eTagArgumentResolver: org.springframework.data.rest.webmvc.support.ETagArgumentResolver
entityLinks: org.springframework.data.rest.webmvc.support.RepositoryEntityLinks
persistentEntityArgumentResolver: org.springframework.data.rest.webmvc.config.PersistentEntityResourceHandlerMethodArgumentResolver
jsonSchemaConverter: org.springframework.data.rest.webmvc.json.PersistentEntityToJsonSchemaConverter
resourceDescriptionMessageSourceAccessor: org.springframework.context.support.MessageSourceAccessor
objectMapper: com.fasterxml.jackson.databind.ObjectMapper
jacksonHttpMessageConverter: org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration$ResourceSupportHttpMessageConverter
halJacksonHttpMessageConverter: org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration$ResourceSupportHttpMessageConverter
halObjectMapper: com.fasterxml.jackson.databind.ObjectMapper
uriListHttpMessageConverter: org.springframework.data.rest.webmvc.convert.UriListHttpMessageConverter
resourceProcessorInvoker: org.springframework.hateoas.mvc.ResourceProcessorInvoker
repositoryExporterHandlerAdapter: org.springframework.data.rest.webmvc.RepositoryRestHandlerAdapter
restHandlerMapping: org.springframework.data.rest.webmvc.support.DelegatingHandlerMapping
resourceMappings: org.springframework.data.rest.core.mapping.RepositoryResourceMappings
linkCollector: org.springframework.data.rest.webmvc.mapping.LinkCollector
excerptProjector: org.springframework.data.rest.webmvc.support.DefaultExcerptProjector
exceptionHandlerExceptionResolver: org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
repositoryRestExceptionHandler: org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler
repositoryInvokerFactory: org.springframework.data.rest.core.support.UnwrappingRepositoryInvokerFactory
defaultMessageConverters: java.util.ArrayList
alpsJsonHttpMessageConverter: org.springframework.data.rest.webmvc.alps.AlpsJsonHttpMessageConverter
pageableResolver: org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver
sortResolver: org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver
backendIdConverterRegistry: org.springframework.plugin.core.OrderAwarePluginRegistry
auditableBeanWrapperFactory: org.springframework.data.auditing.MappingAuditableBeanWrapperFactory
httpHeadersPreparer: org.springframework.data.rest.webmvc.HttpHeadersPreparer
selfLinkProvider: org.springframework.data.rest.core.support.DefaultSelfLinkProvider
associationLinks: org.springframework.data.rest.webmvc.mapping.Associations
enumTranslator: org.springframework.data.rest.webmvc.json.EnumTranslator
alpsConverter: org.springframework.data.rest.webmvc.alps.RootResourceInformationToAlpsDescriptorConverter
profileResourceProcessor: org.springframework.data.rest.webmvc.ProfileResourceProcessor
pagedResourcesAssembler: org.springframework.data.web.PagedResourcesAssembler
pagedResourcesAssemblerArgumentResolver: org.springframework.data.web.PagedResourcesAssemblerArgumentResolver
entityLinksPluginRegistry: org.springframework.plugin.core.OrderAwarePluginRegistry
controllerEntityLinks: org.springframework.hateoas.core.ControllerEntityLinks
jaxRsEntityLinks: org.springframework.hateoas.core.ControllerEntityLinks
delegatingEntityLinks: org.springframework.hateoas.core.DelegatingEntityLinks
_halObjectMapper: com.fasterxml.jackson.databind.ObjectMapper
_linkDiscovererRegistry: org.springframework.plugin.core.OrderAwarePluginRegistry
defaultRelProvider: org.springframework.hateoas.core.EvoInflectorRelProvider
annotationRelProvider: org.springframework.hateoas.core.AnnotationRelProvider
relProviderPluginRegistry: org.springframework.plugin.core.OrderAwarePluginRegistry
_relProvider: org.springframework.hateoas.core.DelegatingRelProvider
springBootRepositoryRestConfigurer: org.springframework.boot.autoconfigure.data.rest.SpringBootRepositoryRestConfigurer
objectPostProcessor: org.springframework.security.config.annotation.configuration.AutowireBeanFactoryObjectPostProcessor
authenticationManagerBuilder: org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
enableGlobalAuthenticationAutowiredConfigurer: org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration$EnableGlobalAuthenticationAutowiredConfigurer
initializeUserDetailsBeanManagerConfigurer: org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer
initializeAuthenticationProviderBeanManagerConfigurer: org.springframework.security.config.annotation.authentication.configuration.InitializeAuthenticationProviderBeanManagerConfigurer
delegatingApplicationListener: org.springframework.security.context.DelegatingApplicationListener
webSecurityExpressionHandler: org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
springSecurityFilterChain: org.springframework.security.web.FilterChainProxy
privilegeEvaluator: org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator
autowiredWebSecurityConfigurersIgnoreParents: org.springframework.security.config.annotation.web.configuration.AutowiredWebSecurityConfigurersIgnoreParents
requestDataValueProcessor: org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor
ignoredPathsWebSecurityConfigurerAdapter: org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration$IgnoredPathsWebSecurityConfigurerAdapter
defaultIgnoredRequestsCustomizer: org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration$DefaultIgnoredRequestCustomizer
authenticationManager: org.springframework.security.authentication.ProviderManager
springBootAuthenticationConfigurerAdapter: org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration$SpringBootAuthenticationConfigurerAdapter
authenticationManagerConfigurationListener: org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration$AuthenticationManagerConfigurationListener
bootGlobalAuthenticationConfigurationAdapter: org.springframework.boot.autoconfigure.security.BootGlobalAuthenticationConfiguration$BootGlobalAuthenticationConfigurationAdapter
authenticationEventPublisher: org.springframework.security.authentication.DefaultAuthenticationEventPublisher
securityProperties: org.springframework.boot.autoconfigure.security.SecurityProperties
halMessageConverterSupportedMediaTypeCustomizer: org.springframework.boot.autoconfigure.hateoas.HypermediaHttpMessageConverterConfiguration$HalMessageConverterSupportedMediaTypesCustomizer
jdbcTemplate: org.springframework.jdbc.core.JdbcTemplate
namedParameterJdbcTemplate: org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
securityFilterChainRegistration: org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean
characterEncodingFilter: org.springframework.boot.web.filter.OrderedCharacterEncodingFilter
localeCharsetMappingsCustomizer: org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration$LocaleCharsetMappingsCustomizer
multipartConfigElement: javax.servlet.MultipartConfigElement
multipartResolver: org.springframework.web.multipart.support.StandardServletMultipartResolver
serverProperties: org.springframework.boot.autoconfigure.web.ServerProperties
duplicateServerPropertiesDetector: org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration$DuplicateServerPropertiesDetector
restTemplateBuilder: org.springframework.boot.web.client.RestTemplateBuilder
payloadRootAnnotationMethodEndpointMapping: org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping
soapActionAnnotationMethodEndpointMapping: org.springframework.ws.soap.server.endpoint.mapping.SoapActionAnnotationMethodEndpointMapping
annotationActionEndpointMapping: org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping
defaultMethodEndpointAdapter: org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter
soapFaultAnnotationExceptionResolver: org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver
simpleSoapExceptionResolver: org.springframework.ws.soap.server.endpoint.SimpleSoapExceptionResolver
messageDispatcherServlet: org.springframework.boot.web.servlet.ServletRegistrationBean
     */
}
