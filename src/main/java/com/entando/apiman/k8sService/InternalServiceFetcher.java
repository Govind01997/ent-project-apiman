package com.entando.apiman.k8sService;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;

@Component
public class InternalServiceFetcher {


   
    @Autowired
    K8ServiceConnector kConnector;


    String ClusterIP;

    String ContextPath;
    
    Integer Port;

    public String fetchService() throws FileNotFoundException, IOException, ApiException {

        CoreV1Api api = kConnector.getV1API();

        V1ServiceList list = api.listNamespacedService("entando", "", null, null, null, "EntandoPlugin", null, null, null, null, null);


        list.getItems().forEach(e->{
        try {

            

            V1PodList ll = api.listNamespacedPod("entando", "pretty", null, null, null,"EntandoPlugin="+ e.getMetadata().getLabels().get("entando.org/deployment").toString(), null, null, null, null, null);
         


            ClusterIP = e.getSpec().getClusterIP();

           e.getSpec().getPorts().stream().forEach(p -> {Port =p.getPort();});


            ll.getItems().forEach(l->{l.getSpec().getContainers().forEach(
                    cd->
                    {
                        // Port = cd.getPorts().get(0).getHostPort().toString();
                        cd.getEnv().
                        forEach(ge->{ 
                            if(ge.getName().equals("SERVER_SERVLET_CONTEXT_PATH"))
                            {
                               ContextPath = ge.getValue();

                               
                            }
        
                        }); }
                    );;} );

                   
        } catch (ApiException e1) {
           
            e1.printStackTrace();
        }
    ;} );


   return ClusterIP+":"+Port+ContextPath;


        
    }
}
