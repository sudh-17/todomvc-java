/**
 * 
 */
(function(window){
	function Ajax(){
		
	}
	
	Ajax.prototype.ajax = function(type,url,data,callback){
		$ajax({
        	type: 'get',
        	url: '/todomvc-java/add.do',
        	data: {title: title},
        	success: function(newItem){
        		callback.call(this,newItem);
        	},
        	error: function(stat){
        		console.error(stat+': request data exception!')
        	}
        });
	}
	
}(window))