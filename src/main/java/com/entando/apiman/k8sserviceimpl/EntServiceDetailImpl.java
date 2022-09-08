package com.entando.apiman.k8sserviceimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.entando.apiman.entity.EntServiceDetail;
import com.entando.apiman.repositories.Repo;
import com.entando.apiman.entity.EmployeeEntity;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Component
public class EntServiceDetailImpl implements com.entando.apiman.k8sService.EntServiceDetail

{
	public String nameSpace = "default";
	
	@Autowired
	K8ServiceConnector kConnector;

	@Autowired
	private Repo repo;
	public List<String> ClusterIP1;

	public String ContextPath;

	public Integer Port;
/*
	@Override
	public List<String> fetchAndSaveK8sServices(String ServiceType)
			throws FileNotFoundException, IOException, ApiException {

		fetchK8sServices("entando", "entando");
		CoreV1Api api = kConnector.getV1API();
		List<List<String>> clusterIP2 = new ArrayList<>();
		List<String> address = new ArrayList<>();
		System.out.println("TOP level");

		String ns = "entando";
		// String ls =
		// "entando.org/deployment=pn-baf96d56-1f31cbd9-akhileshprajapatinv-ent-project-template";
		// String fs = "status.phase=Running";
		V1ServiceList list = api.listNamespacedService(ns, "", null, null, null, null, null, null, null, null, null);
		// System.out.println("123456"+list);
		list.getItems().forEach(e -> {
			try {
				V1PodList ll = api.listNamespacedPod(ns, "pretty", null, null, null, null, null, null, null, null,
						null);
				ClusterIP1 = e.getSpec().getClusterIPs();
				try {

					clusterIP2.add(ClusterIP1);
					System.out.println(clusterIP2);

				} catch (Exception g) {
					System.out.println("no");
				}
				e.getSpec().getPorts().stream().forEach(p -> {
					Port = p.getPort();

				});

				System.out.println(ClusterIP1 + ":" + Port);

//				for (V1Pod item : ll.getItems()) {
//					ContextPath = item.getMetadata().getName();
//				}

				try {

					ll.getItems().forEach(l -> {
						l.getSpec().getContainers().forEach(cd -> {
							// Port = cd.getPorts().get(0).getHostPort().toString();
							cd.getEnv().forEach(ge -> {
//                            if(ge.getName().equals("SERVER_SERVLET_CONTEXT_PATH"))
//                            {
								System.out.println("testing");
								ContextPath = ge.getValue();
								// String address;

								address.add(ClusterIP1 + ":" + Port + "/" + ContextPath);
								System.out.println("Address->" + address);
//                            }

							});
						});
						;
					});
				} catch (Exception e2) {
					System.out.println("no value");
				}

			} catch (ApiException e1) {

				e1.printStackTrace();
			}
			;
		});

		return address;

	}
	
	*/
	
	public List<String> fetchK8sServices(String ServiceType, String nameSpace) throws FileNotFoundException, IOException, ApiException {
		List<List<String>> clusterIP2 = new ArrayList<>();
		CoreV1Api api = kConnector.getV1API();
		List<String> address = new ArrayList<>();
		String ns = "entando";
	//	nameSpace = "entando";

		V1ServiceList list = api.listNamespacedService(nameSpace, "", null, null, null, null, null, null, null, null, null);
		// System.out.println("123456"+list);
		list.getItems().forEach(e -> {
			try {
				V1PodList podList = api.listNamespacedPod(nameSpace, "pretty", null, null, null, null, null, null, null, null,
						null);
				ClusterIP1 = e.getSpec().getClusterIPs();
				try {

					clusterIP2.add(ClusterIP1);

				} catch (Exception g) {
					System.out.println("no");
				}
				
				e.getSpec().getPorts().stream().forEach(p -> {
					Port = p.getPort();

				});

			//	System.out.println(ClusterIP1 + ":" + Port);



				try {

					podList.getItems().forEach(l -> {
						l.getSpec().getContainers().forEach(cd -> {
							// Port = cd.getPorts().get(0).getHostPort().toString();
							cd.getEnv().forEach(ge -> {
	                            if(ge.getName().equals("SERVER_SERVLET_CONTEXT_PATH"))
	                            {

								ContextPath = ge.getValue();
                                 
								address.add(ClusterIP1 + ":" + Port + "/" + ContextPath);
								
                       }

							});
						});
						;
					});
				} catch (Exception e2) {
					System.out.println("no value");
				}

			} catch (ApiException e1) {

				e1.printStackTrace();
			}
			;
		});
		System.out.println("--->"+address);
		return address;
	}
	
/*
	// ============================
	public ArrayList<EntServiceDetail> fetchK8sServices(String ServiceType, String nameSpace)
		throws ApiException, FileNotFoundException, IOException {
		List<List<String>> clusterIP2 = new ArrayList<>();
		CoreV1Api api = kConnector.getV1API();
		List<String> address = new ArrayList<>();
		String ns = "entando";
		nameSpace = "entando";
		
		V1ServiceList list = api.listNamespacedService(ns, "", null, null, null, null, null, null, null, null, null);
		// System.out.println("123456"+list);
		list.getItems().forEach(e -> {
			try {
				V1PodList podList = api.listNamespacedPod(ns, "pretty", null, null, null, null, null, null, null, null,
						null);
				ClusterIP1 = e.getSpec().getClusterIPs();
				try {

					clusterIP2.add(ClusterIP1);

				} catch (Exception g) {
					System.out.println("no");
				}
				e.getSpec().getPorts().stream().forEach(p -> {
					Port = p.getPort();

				});

				System.out.println(ClusterIP1 + ":" + Port);



				try {

					podList.getItems().forEach(l -> {
						l.getSpec().getContainers().forEach(cd -> {
							// Port = cd.getPorts().get(0).getHostPort().toString();
							cd.getEnv().forEach(ge -> {
//	                            if(ge.getName().equals("SERVER_SERVLET_CONTEXT_PATH"))
//	                            {

								ContextPath = ge.getValue();
                                 
								address.add(ClusterIP1 + ":" + Port + "/" + ContextPath);
								
								System.out.println(address);
//	                            }

							});
						});
						;
					});
				} catch (Exception e2) {
					System.out.println("no value");
				}

			} catch (ApiException e1) {

				e1.printStackTrace();
			}
			;
		});

		return null;
	}
*/
	@Override
	public void saveUrl() {
		EntServiceDetailImpl service=new EntServiceDetailImpl();

		System.out.println("inside of saveURL method");
		System.out.println("ContextPath="+service.ContextPath);
	
		
		try {
			for(String f1:this.fetchK8sServices("Internal", "entando"))
			{
				EntServiceDetail obj=new EntServiceDetail();
				obj.setUrl(f1);
				obj.setserviceType1("Internal");
				System.out.println("inside of saveURL"+f1);
				repo.save(obj);
			}
			
			
		} catch (IOException | ApiException e) {
			System.out.println("inside of catch");
			e.printStackTrace();
		}
	}
	
}

/*
 * @Override public void fetchAndSaveK8sServices(String ServiceType) {
 * 
 * 
 * 1.Fetch internal services fetchK8sServices("internal") 2.Save Internal
 * services Repo.save(list) 3.Fetch External services
 * fetchK8sServices("External") 4.Save External services Repo.save(list)
 * 
 * 
 * 
 * 
 * } private ArrayList<EntServiceDetail> fetchK8sServices(String ServiceType) {
 * return null;
 * 
 * List<Entapiman> k8sServices = new ArrayList<Entapiman>(); return k8sServices;
 * 
 * 
 * }
 */


