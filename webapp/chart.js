function makeChart()
{
  //alert('enter');
  //alert(select);
  //alert('exit');
                                              if(select ===  'event')
                                              {
                                                


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
                                                   json['axes']={};
                                                   json['axes']['im_count']='y';
                                                   json['axes']['cl_count']='y2';
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
                                                   axis_json['y2']={};
                                                   //axis_json['y2']['label']={};
                                                   //axis_json['y2']['label']['text']="event-counts";
                                                   //axis_json['y2']['label']['position']='outer-middle';
                                                   axis_json['y2']['show']=true;
                                                   //axis_json['y2']['max']=15;
                                                   //axis_json['y2']['min']=0;
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


                                                    json['size']={};
                                                    //json['size']['height']=1000;
                                                    json['size']['width']=1000;

                                                      //alert("helllo");
                                                      //alert(JSON.stringify(json));

                                                   var div =  document.getElementById('events2');
                                                   div.innerHTML="";
                                                   var div2 =  document.getElementById('event_count2');
                                                   div2.innerHTML="";    
                                                   var chart1 = c3.generate(json);
                                                   json2=json;

                                                   testdata=json;
                                                   //alert(JSON.stringify(json2));
                                                   //
                                                   json2['data']['type']='bar';
                                                   json2['bindto']='#event_count2';
                                                   //alert((JSON.stringify(json2)));
                                                   var chart2 =  c3.generate(json2);

                                                //});
                                              
                                              }
                                              else if(select ===  'device')
                                              {
                                               


                                                  //alert('device');
                                                  var new_json={};//alert('device');

                                                  new_json['columns']=[];//alert('device');

                                                  
                                                  //new_json['columns'].push(browser_name);

                                                  for(var x in  device_name)
                                                  {
                                                    //alert(x);
                                                    new_json['columns'].push([device_name[x],device_counts[x]]);
                                                  }
                                                  new_json['type']='bar';


                                                  json_device['data']=new_json;
                                                  
                                                  json_device['bindto']='#events';
                                                  var axis_x={};

                                                  json_device['axis']={};
                                                  json_device['axis']['x']=axis_x;


                                                  //json_browser['axis']=axis_json;
                                                  
                                                


                                                  //json_browser['data']['type']='bar';
                                                  //alert(JSON.stringify(json_device));
                                                  //json_device['color']={};
                                                  //json_device['color']['pattern']=['#723456']
                                                  var div =  document.getElementById('events2');
                                                   div.innerHTML="";
                                                   var div2 =  document.getElementById('event_count2');
                                                   div2.innerHTML=""; 

                                                   var chart1 = c3.generate(json_device);
                                                  json2=json_device;
                                                   //alert(JSON.stringify(json2));
                                                   //
                                                   json2['data']['type']='pie';
                                                   json2['bindto']='#events2';
                                                   //alert((JSON.stringify(json2)));
                                                   var chart2 =  c3.generate(json2);
                                                  
                                                //});
                          
                                              }
                                              else if(select  ===  'browser')
                                              {
                                                  var new_json={};
                                                  new_json['columns']=[];

                                                  
                                                  //new_json['columns'].push(browser_name);

                                                  for(var x in  browser_name)
                                                  {
                                                    new_json['columns'].push([browser_name[x],browserCount[x]]);
                                                  }
                                                  new_json['type']='bar';


                                                  json_browser['data']=new_json;
                                                  
                                                  json_browser['bindto']='#events';
                                                  var axis_x={};

                                                  json_browser['axis']={};
                                                  json_browser['axis']['x']=axis_x;


                                                  //json_browser['axis']=axis_json;
                                                  
                                                


                                                  //json_browser['data']['type']='bar';
                                                  //alert(JSON.stringify(json_device));
                                                  //json_device['color']={};
                                                  //json_device['color']['pattern']=['#723456']
                                                    var div =  document.getElementById('events2');
                                                   div.innerHTML="";
                                                   var div2 =  document.getElementById('event_count2');
                                                   div2.innerHTML=""; 
                                                  var chart1 = c3.generate(json_browser);
                                                  json2=json_browser;
                                                   //alert(JSON.stringify(json2));
                                                   //
                                                   json2['data']['type']='pie';
                                                   json2['bindto']='#events2';
                                                   //alert((JSON.stringify(json2)));
                                                   var chart2 =  c3.generate(json2);
                                                  
                                                //});
                                              }

                                              else if(select  === 'location')
                                              {
                                                

                                                //alert('location');
                      
                                                  var new_json={};

                                                  new_json['columns']=[];

                                                  
                                                  //new_json['columns'].push(browser_name);

                                                  for(var x in  location_name)
                                                  {
                                                    new_json['columns'].push([location_name[x],location_count[x]]);
                                                  }
                                                  new_json['type']='bar';


                                                  json_location['data']=new_json;
                                                  
                                                  json_location['bindto']='#events';
                                                  var axis_x={};

                                                  json_location['axis']={};
                                                  json_location['axis']['x']=axis_x;


                                                  //json_browser['axis']=axis_json;
                                                  
                                                


                                                  //json_browser['data']['type']='bar';
                                                  //alert(JSON.stringify(json_device));
                                                  //json_device['color']={};
                                                  //json_device['color']['pattern']=['#723456']
                                                  var div =  document.getElementById('events2');
                                                   div.innerHTML="";
                                                   var div2 =  document.getElementById('event_count2');
                                                   div2.innerHTML=""; 


                                                   var chart1 = c3.generate(json_location);
                                                  json2=json_location;
                                                   //alert(JSON.stringify(json2));
                                                   //
                                                   json2['data']['type']='pie';
                                                   json2['bindto']='#events2';
                                                   //alert((JSON.stringify(json2)));
                                                   var chart2 =  c3.generate(json2);
                                                  
                                               // });
                                              }

                                              else if (select === 'os') 
                                              {
                                                  var new_json={};

                                                  new_json['columns']=[];

                                                  
                                                  //new_json['columns'].push(browser_name);

                                                  for(var x in  os_name)
                                                  {
                                                    new_json['columns'].push([os_name[x],os_count[x]]);
                                                  }
                                                  new_json['type']='bar';


                                                  json_os['data']=new_json;
                                                  
                                                  json_os['bindto']='#events';
                                                  var axis_x={};

                                                  json_os['axis']={};
                                                  json_os['axis']['x']=axis_x;


                                                  //json_browser['axis']=axis_json;
                                                  
                                                


                                                  //json_browser['data']['type']='bar';
                                                  //alert(JSON.stringify(json_device));
                                                  //json_device['color']={};
                                                  //json_device['color']['pattern']=['#723456']
                                                  var div =  document.getElementById('events2');
                                                   div.innerHTML="";
                                                   var div2 =  document.getElementById('event_count2');
                                                   div2.innerHTML=""; 


                                                   var chart1 = c3.generate(json_os);
                                                  json2=json_os;
                                                   //alert(JSON.stringify(json2));
                                                   //
                                                   json2['data']['type']='pie';
                                                   json2['bindto']='#events2';
                                                   //alert((JSON.stringify(json2)));
                                                   var chart2 =  c3.generate(json2);
                                                  
                                                //});
                                              }
}