function makeChart(select)
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
                                                   new_json['columns'].push(['IM_COUNT']);
                                                   for(var x in im_count)
                                                   {
                                                      //alert("imcount[x]="+im_count[x]);
                                                      new_json['columns'][1].push(im_count[x]);
                                                   }

                                                   //CL_COUNT
                                                   new_json['columns'].push(['CL_COUNT']);

                                                   for(var x in cl_count)
                                                   {
                                                      //alert('cl_count['+x+']='+cl_count[x]);
                                                      new_json['columns'][2].push(cl_count[x]);
                                                   }

                                                   //CV_COUNT
                                                   new_json['columns'].push(['CV_COUNT']);

                                                   for(var x in cv_count)
                                                   {
                                                      //alert('cl_count['+x+']='+cl_count[x]);
                                                      new_json['columns'][3].push(cv_count[x]);
                                                   }


                                                   new_json['selection']={};
                                                   new_json['selection']['enabled']=true;
                                                   
                                                   json['data']=new_json;

                                                   json['data']['onclick']=function(d)
                                                   {
                                                      console.log("hello");
                                                      alert(d);
                                                   };;


                                                   json['data']['onclick']=function(){ alert('click_'); return "";};
                                                   json['axes']={};
                                                   json['axes']['IM_COUNT']='y';
                                                   json['axes']['CL_COUNT']='y2';
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
                                                   var func = function (d) { return d.getFullYear()+"-"+d.getDate()+"-"+(d.getMonth()+1) + " "+d.getHours()+":00:00";}; 
                                                   tooltip['title']=func;
                                                   //tooltip['value']=function(){ return "helllo";};
                                                   json['tooltip']={};json['tooltip']['format']=tooltip;

                                                   //legend - position
                                                   var legend= {} ;
                                                   legend['position']='right';
                                                   json['legend']= legend;


                                                   //zoom
                                                   var zoom={};
                                                   zoom['enabled']='true';
                                                   //json['zoom']=zoom;


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


                                                    //console.log($(window).width());
                                                                                                        
                                                    var width = $(window).width()/10*9;
                                                    json['size']['width']=  width;

                                                      //alert("helllo");
                                                     // alert(JSON.stringify(json));

                                                       //console.log('hello'); 
                                                     
                                                   event_chart = c3.generate(json);
                                                   json2=json;

                                                   testdata=json;
                                                   

                                                //});
                                              
                                              }
                                              else if(select ===  'device')
                                              {
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
                                                  
                                                  json_device['bindto']='#device';
                                                  var axis_x={};

                                                  json_device['axis']={};
                                                  //json_device['axis']['x']=axis_x;


                                                  //json_browser['axis']=axis_json;
                                                  
                                                


                                                  

                                                  //alert(JSON.stringify(json_device));
                                                  json_device['size']={};
                                                  json_device['size']['height']=300;

                                                  json_device ['tooltip']={};
                                                  json_device ['tooltip']['format']={};
                                                  json_device  ['tooltip']['format']['title']={};
                                                  var  toolFunc = function() { return "device";};
                                                  json_device ['tooltip']['format']['title']= toolFunc;

                                                  //json_device['data']['tooltip']['format']['value']="";
                                                  device_chart= c3.generate(json_device);
                                                  json2=json_device;
                                                   //alert(JSON.stringify(json2));
                                                   //
                                                   
                          
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
                                                  
                                                  json_browser['bindto']='#browser';
                                                  var axis_x={};

                                                  json_browser['axis']={};
                                                  json_browser['axis']['x']=axis_x;



                                                  json_browser['size']={};
                                                  json_browser['size']['height']=300;

                                                  json_browser ['tooltip']={};
                                                  json_browser ['tooltip']['format']={};
                                                  json_browser  ['tooltip']['format']['title']={};
                                                  var  toolFunc = function() { return "browser";};
                                                  json_browser ['tooltip']['format']['title']= toolFunc;


                                                  browser_chart = c3.generate(json_browser);
                                                  json2=json_browser;
                                                  
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
                                                  
                                                  json_location['bindto']='#location';


                                                  json_location['size']={};
                                                  json_location['size']['height']=300;
                                                  

                                                  var axis_x={};

                                                  //json_location['axis']={};
                                                  //json_location['axis']['x']=axis_x;

                                                  json_location ['tooltip']={};
                                                  json_location ['tooltip']['format']={};
                                                  json_location  ['tooltip']['format']['title']={};
                                                  var  toolFunc = function() { return "location";};
                                                  json_location ['tooltip']['format']['title']= toolFunc;


                                                  location_chart = c3.generate(json_location);
                                                  
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
                                                  
                                                  json_os['bindto']='#os';

                                                  json_os['size']={};
                                                  json_os['size']['height']=300;
                                                  
                                                  var axis_x={};

                                                  //json_os['axis']={};
                                                  //json_os['axis']['x']=axis_x;



                                                  json_os ['tooltip']={};
                                                  json_os ['tooltip']['format']={};
                                                  json_os  ['tooltip']['format']['title']={};
                                                  var  toolFunc = function() { return "os";};
                                                  json_os ['tooltip']['format']['title']= toolFunc;
                                                  os_chart = c3.generate(json_os);
                                                  
                                                  
                                                //});
                                              }
}

function   b_bar()
{
  browser_chart.toBar();
}

function  b_donut()
{

  browser_chart.toDonut();
}

function  d_bar()
{
  device_chart.toBar();
}
function  d_donut()
{
  device_chart.toDonut();
}
function  o_bar()
{
  os_chart.toBar();
}
function  o_donut()
{
  os_chart.toDonut();
}
function l_bar()
{
  location_chart.toBar();
}
function l_donut()
{
  location_chart.toDonut();
}