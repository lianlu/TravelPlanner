package Calculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetSubset{
	public List<List<Integer>> subsets(List<Integer> a) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Collections.sort(a);
		List<Integer> list = new ArrayList<>();
		helper(res, list, a, 0);
		return rearrange(res);
	}
	private void helper(List<List<Integer>> res, List<Integer> list, List<Integer> a, int pos) {
		res.add(new ArrayList<Integer>(list));
		for(int i = pos; i < a.size(); i++) {
			list.add(a.get(i));
			helper(res, list, a, i+1);
			list.remove(list.size()-1);
		}
	}
	private List<List<Integer>> rearrange(List<List<Integer>> res) {
		List<List<Integer>> r = new ArrayList<>();
		int max = 0;
		for(List<Integer> list : res) {
			max = Math.max(max, list.size());
		}
		for(int i = 0; i < max+1; i++) {
			for(List<Integer> list : res) {
				if(list.size() == i) {
					r.add(new ArrayList<>(list));;
				}
			}
		}
		return r;
	}
}

