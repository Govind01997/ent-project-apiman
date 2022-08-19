package com.entando.apiman.k8sService;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ServiceList;

public class ExternalServiceFetcher {


   
    @Autowired
    K8ServiceConnector kConnector;


    String ClusterIP;

    String ContextPath;

    String Port;

    
    
    public String fetchService() throws FileNotFoundException, IOException, ApiException {

        CoreV1Api api = kConnector.getV1API();

        V1ServiceList list = api.listNamespacedService("default", "", null, null, null, "app=custom-node", null, null, null, null, null);


        list.getItems().forEach(e->{
      

            Port = e.getSpec().getPorts().get(1).toString();
         

            ClusterIP = e.getSpec().getClusterIP();

          

    });

    return ClusterIP+Port+ContextPath;




        
    }
}
