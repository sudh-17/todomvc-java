/**
 * 
 */
(function(window){
	
	function Store(){}
	
	Store.prototype.add = function(title,callback){
		callback = callback || function () {};
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
	
	Store.prototype.remove = function(id,callback){
		callback = callback || function () {};
		$ajax({
        	type: 'get',
        	url: '/todomvc-java/remove.do',
        	data: {id: id},
        	success: function(id){
        		callback.call(this,id);
        	},
        	error: function(stat){
        		console.error(stat+': request data exception!')
        	}
        });
	}
	
	Store.prototype.update = function(data,callback){
		callback = callback || function () {};
		$ajax({
        	type: 'get',
        	url: '/todomvc-java/update.do',
        	data: data,
        	success: function(updateItem){
        		callback.call(this,updateItem);
        	},
        	error: function(stat){
        		console.error(stat+': request data exception!')
        	}
        });
	}
	
	Store.prototype.getAll = function(callback){
		callback = callback || function () {};
		$ajax({
        	type: 'get',
        	url: '/todomvc-java/getAll.do',
        	success: function(data){
        		var list = data || [];
                callback.call(this, list);
        	},
        	error: function(stat){
        		console.error(stat+': request data exception!')
        	}
        });
	}
	
	Store.prototype.counter = function(callback){
		callback = callback || function () {};
		$ajax({
        	type: 'get',
        	url: '/todomvc-java/getAll.do',
        	success: function(list){
        		var counter = {};
                counter.undone = 0;
                counter.done = 0;
                counter.total = list.length;
                for(let i = 0;i < list.length; i++){
                    if(list[i].completed == false){
                        counter.undone ++;
                    }
                    else{
                        counter.done ++;
                    }
                }
                callback.call(this,counter);
        	},
        	error: function(stat){
        		console.error(stat+': request data exception!')
        	}
        });
	}
	
	
	window.app = window.app || {};
	window.app.Store = Store;
	
}(window))