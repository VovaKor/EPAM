package com.korobko;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import java.io.*;
import java.net.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author Vova Korobko
 */
public class URLServiceTest {

    public static final String MALFORMED_URL = "http";
    public static final String HTTP_LOCALHOST = "http://localhost/";
    private static URLStreamHandlerFactory urlStreamHandlerFactory;
    private String testUrlContent = "<a href=\"https://tmfeed.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">TM Feed</a>\n" +
            "      <a href=\"https://habrahabr.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\" class=\"current\">Хабрахабр</a>\n" +
            "      <a href=\"https://geektimes.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Geektimes</a>\n" +
            "      <a href=\"https://toster.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Тостер</a>\n" +
            "      <a href=\"https://moikrug.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Мой круг</a>\n" +
            "      <a href=\"https://freelansim.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Фрилансим</a>";
    private String expectedUrlTestContent = "<a href=\"https://tmfeed.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">TM Feed</a>      <a href=\"https://habrahabr.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\" class=\"current\">Хабрахабр</a>      <a href=\"https://geektimes.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Geektimes</a>      <a href=\"https://toster.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Тостер</a>      <a href=\"https://moikrug.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Мой круг</a>      <a href=\"https://freelansim.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Фрилансим</a>";
    private String urlTestString = "1) <a href=\"https://habrahabr.ru/post/343550/\" class= 2)<a href=\"https://habrahabr.ru/post/3143550/\" class=3)<a href=\"https://habrahabr.ru/post/1343550/\" class=4)<a href=\"https://habrahabr.ru/post/3413550/\" class=5)<a href=\"https://habrahabr.ru/post/3431550/\" class=6)<a href=\"https://habrahabr.ru/post/3435150/\" class=7)<a href=\"https://habrahabr.ru/post/3435510/\" class=8)<a href=\"https://habrahabr.ru/post/3435501/\" class=9)<a href=\"https://habrahabr.ru/post/3435502/\" class=10)<a href=\"https://habrahabr.ru/post/3435520/\" class=11)<a href=\"https://habrahabr.ru/post/3435250/\" class=12)<a href=\"https://habrahabr.ru/post/3432550/\" class=13)<a href=\"https://habrahabr.ru/post/3423550/\" class=14)<a href=\"https://habrahabr.ru/post/3243550/\" class=15)<a href=\"https://habrahabr.ru/post/2343550/\" class=16)<a href=\"https://habrahabr.ru/post/3343550/\" class=17)<a href=\"https://habrahabr.ru/post/33343550/\" class=18)<a href=\"https://habrahabr.ru/post/3433550/\" class=19)<a href=\"https://habrahabr.ru/post/34333550/\" class=20)<a href=\"https://habrahabr.ru/post/3435350/\" class=";
    private String urlWithSpace = "href=\"https://habrahabr .ru/post/343550/";

    @BeforeClass
    public static void beforeClass() {
        urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        URL.setURLStreamHandlerFactory(urlStreamHandlerFactory);

    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUrlContentAsString_givenSixLines_shouldReturnOneString() throws Exception {
        AbstractPublicStreamHandler publicStreamHandler = mock(AbstractPublicStreamHandler.class);
        doReturn(publicStreamHandler).when(urlStreamHandlerFactory).createURLStreamHandler(Matchers.eq("http"));

        URLConnection mockedConnection = mock(URLConnection.class);
        doReturn(mockedConnection).when(publicStreamHandler).openConnection(Matchers.any(URL.class));

        doReturn(new ByteArrayInputStream(testUrlContent.getBytes("UTF-8"))).when(mockedConnection).getInputStream();

        String result = URLService.getUrlContentAsString(HTTP_LOCALHOST);
        assertEquals(expectedUrlTestContent, result);
    }

    @Test
    public void getUrlContentAsString_givenWrongURL_shouldThrowException() throws Exception {

        String result = URLService.getUrlContentAsString(MALFORMED_URL);
        assertEquals("", result);
    }

    @Test
    public void extractUrlsFromContent_givenString_returns20Urls() {
        assertEquals(20, URLService.extractUrlsFromContent(urlTestString).size());
    }

    @Test
    public void extractUrlsFromContent_givenUrlWithSpace_skipsUrl() {
        assertEquals(0, URLService.extractUrlsFromContent(urlWithSpace).size());
    }

    private abstract class AbstractPublicStreamHandler extends URLStreamHandler {
        @Override
        public URLConnection openConnection(URL url) throws IOException {
            return null;
        }
    }
}