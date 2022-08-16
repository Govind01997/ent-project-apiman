package com.entando.apiman.k8sService;

import java.io.FileReader;
import java.io.IOException;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

public class PodList
{
	public static void podList() throws IOException, ApiException
	{
	String kubeConfigPath = "jk";
	ApiClient client =
	ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
	// set the global default api-client to the in-cluster one from above
	Configuration.setDefaultApiClient(client);
	// the CoreV1Api loads default api-client from global configuration.
	CoreV1Api api = new CoreV1Api();
	// invokes the CoreV1Api client
	V1PodList list = api.listNamespacedPod("entando", "pretty", null, null, null, "EntandoPlugin", null, null, null, null, null);



    list.getItems().forEach(e->{System.out.println(e.getStatus().getPodIP());} );
//	 list.getItems().forEach(e->{System.out.println(e.getMetadata().getGenerateName());} );


	// V1ServiceList s = api.listNamespacedService("entando", "true", null, null, null, null, null, null, null, null, null);
	// s.getItems().forEach(e -> {System.out.println(e.getSpec().getClusterIP());});
	// System.out.println(s);
	// List<String> ls = new ArrayList<String>();
	// list.getItems().forEach(I-> ls.add(I.getStatus().getPodIP()));
	// System.out.println(list);
}
}
