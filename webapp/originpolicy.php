<?php
/*//$json = file_get_contents('php://input')
$data = file_get_contents('php://input');
// http://10.65.248.196:8080/druid/v2/');
$json = json_decode($data);
$ch = curl_init($json[1]);*/

$ch=curl_init();
$flagValue=0;
$tsflagvalue=0;
$tsendfl=0;
if(isset($_GET["OfferVID"]))
{
	$flagValue=3;
$ovid=$_GET["OfferVID"];
$oid=$_GET["OfferID"];
$cid=$_GET["CampaignID"];
//echo($cid);
}
else if(isset($_GET["OfferID"]))
{
	$flagValue=2;
$oid=$_GET["OfferID"];
$cid=$_GET["CampaignID"];
//echo($cid);
}
else if(isset($_GET["CampaignID"]))
{
	$flagValue = 1;
	$cid=$_GET["CampaignID"];
}
if(isset($_GET["StartDate"]))
{
	$tsflagvalue=1;
	$tsstart=$_GET["StartDate"];
}
if(isset($_GET["EndDate"]))
{
	$tsflagvalue=2;
	$tsend=$_GET["EndDate"];
}
if($flagValue==0)
$url="http://10.65.255.114:8080/CampaignMetricServer/rest/info/campaign";
elseif ($flagValue==1) {
	$url="http://10.65.255.114:8080/CampaignMetricServer/rest/info/CampaignID=".$cid;
	# code...
}
elseif ($flagValue==2) {
	$url="http://10.65.255.114:8080/CampaignMetricServer/rest/info/CampaignID=".$cid."&OfferID=".$oid;
	# code...
}
elseif ($flagValue==3) {
	$url="http://10.65.255.114:8080/CampaignMetricServer/rest/info/CampaignID=".$cid."&OfferID=".$oid."&OfferVID=".$ovid;
	# code...
}
if($tsflagvalue==2)
{
	$url =  $url ."&StartDate=".$tsstart."&EndDate=".$tsend;
}

//echo ($cid);
curl_setopt ($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
//curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode($json[0]) );
curl_setopt( $ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
# Return response instead of printing.
curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );                                                                                                                                                                                                                                                                                                         

$result = curl_exec($ch);

//$json = $_POST['json'];
//$arr = array('a' => 1, 'b' => 2, 'c' => 3, 'd' => 4, 'e' => 5);

//$json = json_encode($data);

header('Content-Type: application/json');
echo ($result);
?>
