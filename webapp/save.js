			<script type="text/javascript">
													$(document).ready(function(){
																				  //$("button").click(function(){
																				    $.ajax({url:"http://192.168.56.1/originpolicy.php",success:function(result){
																				      //alert(JSON.stringify(result[1]));
																				      makeEvent(result);
																				    }});
																				  //});
																				});
													function makeEvent(data)
													{
														var  im_count = [], cl_count =[], cv_count = [], browserCount=[],timestamp=[];
														var json={};
														
														//d3.json("event.json",function(data)
														//{
															
																
															for(var i in data)
															{
																im_count.push(data [i].IM_COUNT);
																//alert("im_count["+i+"]="+data[i].IM_COUNT);
																cl_count.push(data [i].CL_COUNT);
																cv_count.push(data [i].CV_COUNT);	
																timestamp.push(data[i].timestamp);
																//browserCount.push(data[i].br)
															}



															var new_json={};
															new_json['x']='date';
															new_json['x_format']='%Y-%m-%d %H:%M:%S';

															new_json['columns']=[];
															
															//timestamp
															new_json['columns'].push(['date']);
															for( var x in  timestamp)
															{
																new_json['columns'][0].push(timestamp[x]);
															}

															//IM_COUNT
															new_json['columns'].push(['im_count']);
															for(var x in im_count)
															{
																//alert("imcount[x]="+im_count[x]);
																new_json['columns'][1].push(im_count[x]);
															}

															//CL_COUNT
															new_json['columns'].push(['cl_count']);

															for(var x in cl_count)
															{
																//alert('cl_count['+x+']='+cl_count[x]);
																new_json['columns'][2].push(cl_count[x]);
															}


															json['data']=new_json;
															json['bindto']='#events';
															var axis_json = {};
															//axis_json['x']={};

															//type of graph
															var type_json = {};
															type_json['type'] ='timeseries';
															axis_json['x']=type_json;
															axis_json['x']['label']={};

															axis_json['x']['label']['text']='time';
															axis_json['x']['label']['position']='outer-center';
															axis_json['y']={};
															axis_json['y']['label']={};
															axis_json['y']['label']['text']="event-counts";
															axis_json['y']['label']['position']='outer-middle';
															json['axis']=axis_json;

															//tooltip
															var tooltip = {};
															var func = 'function (d) { return d.getFullYear()+"-"+d.getDate()+"-"+(d.getMonth()+1);}'; 
															tooltip['title']=func;
															//json['format']=tooltip;

															//legend - position
															var legend= {} ;
															legend['position']='right';
															json['legend']= legend;


															//zoom
															var zoom={};
															zoom['enabled']='true';
															json['zoom']=zoom;


															 //grid-lines
															 var grid={};
															 var json_x={}, json_y={};
															 json_x['show']='true';

															 json_y['show']='true';
															 //json_y['lines']=[{'value':0}];
															 grid['x']=json_x;
															 grid['y']=json_y;
															 json['grid']=grid;

										   					//alert("helllo");
										   					//alert(JSON.stringify(json));


															var chart1 = c3.generate(json);
														//});
													}	
													</script>
										