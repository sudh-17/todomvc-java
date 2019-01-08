/**
 * author sdh
 */
(function(window){
    function Model(store){
    	this.store = store;
    }

    Model.prototype.add = function(title,callback){
        this.store.add(title,callback);
    }

    Model.prototype.remove = function(id,callback){
        this.store.remove(id,callback);
    }

    Model.prototype.update = function(id,title,completed,callback){
        var data = {
        		id: id,
        		title: title,
        		completed: completed
        };
    	this.store.update(data,callback);
        
    }

    Model.prototype.changeAllStatus = function(status,callback){
        /*callback = callback || function () {};
        var list = JSON.parse(localStorage.getItem(this._dbName));
        for(let i = 0; i < list.length; i++){
            list[i].completed = status;
        }
        localStorage.setItem(this._dbName,JSON.stringify(list));
        callback.call(this,list);*/
    }

    Model.prototype.read = function(callback,equals, filter){
        this.store.getAll(callback);
    }

    Model.prototype.counter = function(callback){
       this.store.counter(callback);
    }

    
    window.app = window.app || {};
    window.app.Model = Model;
}(window))