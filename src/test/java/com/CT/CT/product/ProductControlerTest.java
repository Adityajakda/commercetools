package com.CT.CT.product;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ProductControlerTest {
    /**
     * Method under test: {@link ProductControler#createProduct(ProductData)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateProduct() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Illegal character in path at index 7: Project Key/products?limit=25
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   java.lang.IllegalArgumentException: Illegal character in path at index 7: Project Key/products?limit=25
        //       at java.net.URI.create(URI.java:906)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.buildHttpRequest(ByProjectKeyProductsGet.java:65)
        //       at io.vrap.rmf.base.client.ApiMethod.createHttpRequest(ApiMethod.java:266)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:364)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.executeBlocking(ByProjectKeyProductsGet.java:71)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:337)
        //       at com.CT.CT.product.ProductControler.productPagedQuerywithlimit(ProductControler.java:44)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   java.net.URISyntaxException: Illegal character in path at index 7: Project Key/products?limit=25
        //       at java.net.URI$Parser.fail(URI.java:2974)
        //       at java.net.URI$Parser.checkChars(URI.java:3145)
        //       at java.net.URI$Parser.parseHierarchical(URI.java:3227)
        //       at java.net.URI$Parser.parse(URI.java:3186)
        //       at java.net.URI.<init>(URI.java:623)
        //       at java.net.URI.create(URI.java:904)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.buildHttpRequest(ByProjectKeyProductsGet.java:65)
        //       at io.vrap.rmf.base.client.ApiMethod.createHttpRequest(ApiMethod.java:266)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:364)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.executeBlocking(ByProjectKeyProductsGet.java:71)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:337)
        //       at com.CT.CT.product.ProductControler.productPagedQuerywithlimit(ProductControler.java:44)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductControler productControler = new ProductControler();
        productControler.createProduct(new ProductData());
    }

    /**
     * Method under test: {@link ProductControler#createProduct(ProductData)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateProduct2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Illegal character in path at index 7: Project Key/products?limit=25
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   java.lang.IllegalArgumentException: Illegal character in path at index 7: Project Key/products?limit=25
        //       at java.net.URI.create(URI.java:906)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.buildHttpRequest(ByProjectKeyProductsGet.java:65)
        //       at io.vrap.rmf.base.client.ApiMethod.createHttpRequest(ApiMethod.java:266)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:364)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.executeBlocking(ByProjectKeyProductsGet.java:71)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:337)
        //       at com.CT.CT.product.ProductControler.productPagedQuerywithlimit(ProductControler.java:44)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   java.net.URISyntaxException: Illegal character in path at index 7: Project Key/products?limit=25
        //       at java.net.URI$Parser.fail(URI.java:2974)
        //       at java.net.URI$Parser.checkChars(URI.java:3145)
        //       at java.net.URI$Parser.parseHierarchical(URI.java:3227)
        //       at java.net.URI$Parser.parse(URI.java:3186)
        //       at java.net.URI.<init>(URI.java:623)
        //       at java.net.URI.create(URI.java:904)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.buildHttpRequest(ByProjectKeyProductsGet.java:65)
        //       at io.vrap.rmf.base.client.ApiMethod.createHttpRequest(ApiMethod.java:266)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:364)
        //       at com.commercetools.api.client.ByProjectKeyProductsGet.executeBlocking(ByProjectKeyProductsGet.java:71)
        //       at io.vrap.rmf.base.client.ApiMethod.executeBlocking(ApiMethod.java:337)
        //       at com.CT.CT.product.ProductControler.productPagedQuerywithlimit(ProductControler.java:44)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ProductControler()).createProduct(mock(ProductData.class));
    }
}

