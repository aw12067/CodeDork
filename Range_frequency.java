class RangeFreqQuery 
{
    HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
    public RangeFreqQuery(int[] arr) 
    {
        for(int i=0;i<arr.length;i++)
        {
            if(!map.containsKey(arr[i]))
            {
                map.put(arr[i],new ArrayList<Integer>());
            }
            map.get(arr[i]).add(i);
        }
    }
    
    public int query(int left, int right, int value) 
    {
        ArrayList<Integer> indices = map.get(value);
        if(indices == null || left > indices.get(indices.size()-1) || right < indices.get(0))
        {
            return 0;
        }        
        System.out.println(upper(indices, right)+" "+lower(indices, left));
        return upper(indices, right)-lower(indices, left)+1;
    }
    
    public int lower(ArrayList<Integer> indices, int key)
    {
        int left = 0, right = indices.size()-1; 
        if(key < indices.get(0))
        {
            return 0;
        }
        
        while(left < right)
        {
            int mid = (left+right)/2;
            if(indices.get(mid) < key)
            {
                left = mid + 1;
            }
            else 
            {
                right = mid;
            }
        }
        return left;
    }
    
    public int upper(ArrayList<Integer> indices, int key)
    {
        int left = 0, right = indices.size()-1; 
        if(key > indices.get(right))
        {
            return right;
        }
        
        while(left < right)
        {
            int mid = (left+right)/2+1;
            if(indices.get(mid) > key)
            {
                right = mid - 1;
            }
            else 
            {
                left = mid;
            }
        }
        return left;
    }    
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
