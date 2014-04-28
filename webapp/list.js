function option(opt)
{
	select = opt;
	if(opt === 'event')
	{
		makeChart();
		//alert('event');
	}
	else if(opt === 'browser')
	{
		makeChart();
		//alert(opt);
	}
	else if(opt === 'device')
	{
		makeChart();
		//alert(opt);
	}
	else if(opt ===  'location')
	{

		makeChart();
		//alert(opt);
	}
	else if(opt ===  'os')
	{
		makeChart();
		//alert(opt);
	}
	else
	{
		alert('error');
	}

	var x =  document.getElementById('dashboard');

	x.innerHTML = select.toUpperCase();
	
	//alert(select);
}