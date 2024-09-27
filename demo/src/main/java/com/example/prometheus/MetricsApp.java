package com.example.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

public class MetricsApp {

    // Contador de requisições
    static final Counter requestCounter = Counter.build()
            .name("requests_total")
            .help("Total de requisições processadas.")
            .register();

    public static void main(String[] args) throws IOException {
        // Inicia o servidor HTTP na porta 8080 para expor as métricas
        HTTPServer server = new HTTPServer(8080);

        // Expor métricas da JVM (memória, garbage collector, etc.)
        DefaultExports.initialize();

        // Simular a aplicação processando requisições
        for (int i = 0; i < 100; i++) {
            processRequest();
        }
    }

    private static void processRequest() {
        // Simula o processamento de uma requisição e incrementa o contador
        requestCounter.inc();
        try {
            Thread.sleep(1000); // Simula um tempo de resposta
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
