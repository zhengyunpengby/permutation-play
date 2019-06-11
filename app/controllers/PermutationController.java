package controllers;

import play.mvc.*;

import com.alibaba.fastjson.JSON;

import permutations.Permutation;
import permutations.PermutationType;
import permutations.PermutationStream;
import permutations.impl.InsertPermutation;
import permutations.impl.ForeachPermutation;
import permutations.impl.IncreasePermutation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class PermutationController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public Result insert(Integer num) {
		Permutation permutation = new InsertPermutation(num);
        Map<String,Object> data = new HashMap<>();
        Set<String> set = permutation.generate();
        data.put("count",set.size());
        data.put("data",set);        
        data.put("type",PermutationType.INSERT.getName());        
        return ok(JSON.toJSONString(data));
    }
	
	public Result foreach(Integer num) {
		Permutation permutation = new ForeachPermutation(num);
        Map<String,Object> data = new HashMap<>();
        Set<String> set = permutation.generate();
        data.put("count",set.size());
        data.put("data",set);
		data.put("type",PermutationType.FOREACH.getName()); 
        return ok(JSON.toJSONString(data));
    }

    public Result increase(int num){
        return ok(views.html.increase.render(num));
    }
	
	public Result increaseInit(int num) {
		PermutationStream insertPermutation = new IncreasePermutation(num);
        return ok(insertPermutation.generate());
    }

	public Result increaseIncr(String permutation) {
		PermutationStream insertPermutation = new IncreasePermutation(permutation);
        return ok(insertPermutation.generate());
    }
	
	
}
