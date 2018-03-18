
import java.util.*;

/**
 * @author = Akshay Nalwaya
 * Student ID = 200159155
 */

class AddCityInfo{
    String city;
    double lat;
    double lon;
    
    AddCityInfo(String a,Double b, Double c){
        city = a;
        lat = b;
        lon = c;
    }
    
    @Override
    public String toString(){
        return "city = " + city + " : latitude = " + lat + " : longitude = " + lon;
    }
}

class AddRoute{
    int dest;
    double dist;
    
    AddRoute(int d, double l){
        dest = d;
        dist = l;
    }
    
    @Override
    public String toString(){
        return "destination = "+dest+", distance = "+dist;
    }
}

class queue_node{
    double g,h,f;
    ArrayList<Integer> path = new ArrayList();
    
    queue_node(double g_cost, double h_cost){
        g = g_cost;
        h = h_cost;  
    }
    queue_node(double g_cost, double h_cost, ArrayList curr_path){
        g = g_cost;
        h = h_cost;
        f = g + h;
        path = curr_path;
    }
    
    public void add_path(int src, int dest){
        int temp = SearchUSA.adj_list[src].indexOf(dest);
        g = g + SearchUSA.adj_list[src].get(temp).dist;
        
        h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(src).lat, SearchUSA.city_list.get(src).lon,
                SearchUSA.city_list.get(dest).lat,SearchUSA.city_list.get(dest).lon);
        f = g + h;
        
    }    
    @Override
    public String toString(){
        return "f = "+f+", path = "+path;
    }   
    /*
    @Override
    public int compare(Object o1, Object o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}

class compare_dist_a_star implements Comparator<Object>{
    
    @Override
    public int compare(Object obj1,Object obj2){
        queue_node a = (queue_node)obj1;
        queue_node b = (queue_node)obj2;
        if((a.g+a.h)<(b.g+b.h))
            return -1;
        else if((a.g+a.h)>(b.g+b.h))
            return 1;
        else return 0;
    }
}

class compare_dist_greedy implements Comparator<Object>{
    
    @Override
    public int compare(Object obj1,Object obj2){
        queue_node a = (queue_node)obj1;
        queue_node b = (queue_node)obj2;
        if((a.h)<(b.h))
            return -1;
        else if((a.h)>(b.h))
            return 1;
        else return 0;
    }
}

class compare_dist_uniform implements Comparator<Object>{
    
    @Override
    public int compare(Object obj1,Object obj2){
        queue_node a = (queue_node)obj1;
        queue_node b = (queue_node)obj2;
        if(a.g<b.g)
            return -1;
        else if(a.g>b.g)
            return 1;
        else return 0;
    }
}


class SearchUSA {
    
    public static LinkedList<AddRoute>[] adj_list = new LinkedList[112];
    public static LinkedList<AddCityInfo> city_list = new LinkedList<>();

    
    public static void main(String[] args) {
        
        //Comparator<Object> q_n = new compare_dist();
        //PriorityQueue<queue_node> path_a_star = new PriorityQueue<>(200, q_n);
        
        /* Test */
        /*
        path_a_star.add(new queue_node(1,2.3));
        
        path_a_star.add(new queue_node(7,2));
        path_a_star.add(new queue_node(2,0.43));
        path_a_star.add(new queue_node(5,21.3));
        path_a_star.add(new queue_node(14,2.83));

        while(!path_a_star.isEmpty()){
            queue_node testQN = path_a_star.poll();
        System.out.println(testQN.g + testQN.h);
        }
       
        /* Test */
        
        
        //creating and populating the city information
        AddCityInfo city1 = new AddCityInfo("albanyGA",31.58,84.17);
        city_list.add(city1);
        city1 = new AddCityInfo("albanyNY",42.66, 73.78);
        city_list.add(city1);
        city1 = new AddCityInfo("albuquerque",35.11, 106.61);
        city_list.add(city1);
        city1 = new AddCityInfo("atlanta",33.76, 84.40);
        city_list.add(city1);
        city1 = new AddCityInfo("augusta",33.43, 82.02);
        city_list.add(city1);
        city1 = new AddCityInfo("austin",30.3, 97.75);
        city_list.add(city1);
        city1 = new AddCityInfo("bakersfield",35.36, 119.03);
        city_list.add(city1);
        city1 = new AddCityInfo("baltimore",39.31, 76.62);
        city_list.add(city1);
        city1 = new AddCityInfo("batonRouge",30.46, 91.14);
        city_list.add(city1);
        city1 = new AddCityInfo("beaumont",30.08, 94.13);
        city_list.add(city1);
        city1 = new AddCityInfo("boise",43.61, 116.24);
        city_list.add(city1);
        city1 = new AddCityInfo("boston",42.32, 71.09);
        city_list.add(city1);
        city1 = new AddCityInfo("buffalo",42.9, 78.85);
        city_list.add(city1);
        city1 = new AddCityInfo("calgary",51.0, 114.00);
        city_list.add(city1);
        city1 = new AddCityInfo("charlotte",35.21, 80.83);
        city_list.add(city1);
        city1 = new AddCityInfo("chattanooga",35.05, 85.27);
        city_list.add(city1);
        city1 = new AddCityInfo("chicago",41.84, 87.68);
        city_list.add(city1);
        city1 = new AddCityInfo("cincinnati",39.14, 84.50);
        city_list.add(city1);
        city1 = new AddCityInfo("cleveland",41.48, 81.67);
        city_list.add(city1);
        city1 = new AddCityInfo("coloradoSprings",38.86, 104.79);
        city_list.add(city1);
        city1 = new AddCityInfo("columbus",39.99, 82.99);
        city_list.add(city1);
        city1 = new AddCityInfo("dallas",32.8, 96.79);
        city_list.add(city1);
        city1 = new AddCityInfo("dayton",39.76, 84.20);
        city_list.add(city1);
        city1 = new AddCityInfo("daytonaBeach",29.21, 81.04);
        city_list.add(city1);
        city1 = new AddCityInfo("denver",39.73, 104.97);
        city_list.add(city1);
        city1 = new AddCityInfo("desMoines",41.59, 93.62);
        city_list.add(city1);
        city1 = new AddCityInfo("elPaso",31.79, 106.42);
        city_list.add(city1);
        city1 = new AddCityInfo("eugene",44.06, 123.11);
        city_list.add(city1);
        city1 = new AddCityInfo("europe",48.87, -2.33);
        city_list.add(city1);
        city1 = new AddCityInfo("ftWorth",32.74, 97.33);
        city_list.add(city1);
        city1 = new AddCityInfo("fresno",36.78, 119.79);
        city_list.add(city1);
        city1 = new AddCityInfo("grandJunction",39.08, 108.56);
        city_list.add(city1);
        city1 = new AddCityInfo("greenBay",44.51, 88.02);
        city_list.add(city1);
        city1 = new AddCityInfo("greensboro",36.08, 79.82);
        city_list.add(city1);
        city1 = new AddCityInfo("houston",29.76, 95.38);
        city_list.add(city1);
        city1 = new AddCityInfo("indianapolis",39.79, 86.15);
        city_list.add(city1);
        city1 = new AddCityInfo("jacksonville",30.32, 81.66);
        city_list.add(city1);
        city1 = new AddCityInfo("japan",35.68, 220.23);
        city_list.add(city1);
        city1 = new AddCityInfo("kansasCity",39.08, 94.56);
        city_list.add(city1);
        city1 = new AddCityInfo("keyWest",24.56, 81.78);
        city_list.add(city1);
        city1 = new AddCityInfo("lafayette",30.21, 92.03);
        city_list.add(city1);
        city1 = new AddCityInfo("lakeCity",30.19, 82.64);
        city_list.add(city1);
        city1 = new AddCityInfo("laredo",27.52, 99.49);
        city_list.add(city1);
        city1 = new AddCityInfo("lasVegas",36.19, 115.22);
        city_list.add(city1);
        city1 = new AddCityInfo("lincoln",40.81, 96.68);
        city_list.add(city1);
        city1 = new AddCityInfo("littleRock",34.74, 92.33);
        city_list.add(city1);
        city1 = new AddCityInfo("losAngeles",34.03, 118.17);
        city_list.add(city1);
        city1 = new AddCityInfo("macon",32.83, 83.65);
        city_list.add(city1);
        city1 = new AddCityInfo("medford",42.33, 122.86);
        city_list.add(city1);
        city1 = new AddCityInfo("memphis",35.12, 89.97);
        city_list.add(city1);
        city1 = new AddCityInfo("mexia",31.68, 96.48);
        city_list.add(city1);
        city1 = new AddCityInfo("mexico",19.4, 99.12);
        city_list.add(city1);
        city1 = new AddCityInfo("miami",25.79, 80.22);
        city_list.add(city1);
        city1 = new AddCityInfo("midland",43.62, 84.23);
        city_list.add(city1);
        city1 = new AddCityInfo("milwaukee",43.05, 87.96);
        city_list.add(city1);
        city1 = new AddCityInfo("minneapolis",44.96, 93.27);
        city_list.add(city1);
        city1 = new AddCityInfo("modesto",37.66, 120.99);
        city_list.add(city1);
        city1 = new AddCityInfo("montreal",45.5, 73.67);
        city_list.add(city1);
        city1 = new AddCityInfo("nashville",36.15, 86.76);
        city_list.add(city1);
        city1 = new AddCityInfo("newHaven",41.31, 72.92);
        city_list.add(city1);
        city1 = new AddCityInfo("newOrleans",29.97, 90.06);
        city_list.add(city1);
        city1 = new AddCityInfo("newYork",40.7, 73.92);
        city_list.add(city1);
        city1 = new AddCityInfo("norfolk",36.89, 76.26);
        city_list.add(city1);
        city1 = new AddCityInfo("oakland",37.8, 122.23);
        city_list.add(city1);
        city1 = new AddCityInfo("oklahomaCity",35.48, 97.53);
        city_list.add(city1);
        city1 = new AddCityInfo("omaha",41.26, 96.01);
        city_list.add(city1);
        city1 = new AddCityInfo("orlando",28.53, 81.38);
        city_list.add(city1);
        city1 = new AddCityInfo("ottawa",45.42, 75.69);
        city_list.add(city1);
        city1 = new AddCityInfo("pensacola",30.44, 87.21);
        city_list.add(city1);
        city1 = new AddCityInfo("philadelphia",40.72, 76.12);
        city_list.add(city1);
        city1 = new AddCityInfo("phoenix",33.53, 112.08);
        city_list.add(city1);
        city1 = new AddCityInfo("pittsburgh",40.4, 79.84);
        city_list.add(city1);
        city1 = new AddCityInfo("pointReyes",38.07, 122.81);
        city_list.add(city1);
        city1 = new AddCityInfo("portland",45.52, 122.64);
        city_list.add(city1);
        city1 = new AddCityInfo("providence",41.8, 71.36);
        city_list.add(city1);
        city1 = new AddCityInfo("provo",40.24, 111.66);
        city_list.add(city1);
        city1 = new AddCityInfo("raleigh",35.82, 78.64);
        city_list.add(city1);
        city1 = new AddCityInfo("redding",40.58, 122.37);
        city_list.add(city1);
        city1 = new AddCityInfo("reno",39.53, 119.82);
        city_list.add(city1);
        city1 = new AddCityInfo("richmond",37.54, 77.46);
        city_list.add(city1);
        city1 = new AddCityInfo("rochester",43.17, 77.61);
        city_list.add(city1);
        city1 = new AddCityInfo("sacramento",38.56, 121.47);
        city_list.add(city1);
        city1 = new AddCityInfo("salem",44.93, 123.03);
        city_list.add(city1);
        city1 = new AddCityInfo("salinas",36.68, 121.64);
        city_list.add(city1);
        city1 = new AddCityInfo("saltLakeCity",40.75, 111.89);
        city_list.add(city1);
        city1 = new AddCityInfo("sanAntonio",29.45, 98.51);
        city_list.add(city1);
        city1 = new AddCityInfo("sanDiego",32.78, 117.15);
        city_list.add(city1);
        city1 = new AddCityInfo("sanFrancisco",37.76, 122.44);
        city_list.add(city1);
        city1 = new AddCityInfo("sanJose",37.3, 121.87);
        city_list.add(city1);
        city1 = new AddCityInfo("sanLuisObispo",35.27, 120.66);
        city_list.add(city1);
        city1 = new AddCityInfo("santaFe",35.67, 105.96);
        city_list.add(city1);
        city1 = new AddCityInfo("saultSteMarie",46.49, 84.35);
        city_list.add(city1);
        city1 = new AddCityInfo("savannah",32.05, 81.10);
        city_list.add(city1);
        city1 = new AddCityInfo("seattle",47.63, 122.33);
        city_list.add(city1);
        city1 = new AddCityInfo("stLouis",38.63, 90.24);
        city_list.add(city1);
        city1 = new AddCityInfo("stamford",41.07, 73.54);
        city_list.add(city1);
        city1 = new AddCityInfo("stockton",37.98, 121.30);
        city_list.add(city1);
        city1 = new AddCityInfo("tallahassee",30.45, 84.27);
        city_list.add(city1);
        city1 = new AddCityInfo("tampa",27.97, 82.46);
        city_list.add(city1);
        city1 = new AddCityInfo("thunderBay",48.38, 89.25);
        city_list.add(city1);
        city1 = new AddCityInfo("toledo",41.67, 83.58);
        city_list.add(city1);
        city1 = new AddCityInfo("toronto",43.65, 79.38);
        city_list.add(city1);
        city1 = new AddCityInfo("tucson",32.21, 110.92);
        city_list.add(city1);
        city1 = new AddCityInfo("tulsa",36.13, 95.94);
        city_list.add(city1);
        city1 = new AddCityInfo("uk1",51.3, 0.00);
        city_list.add(city1);
        city1 = new AddCityInfo("uk2",51.3, 0.00);
        city_list.add(city1);
        city1 = new AddCityInfo("vancouver",49.25, 123.10);
        city_list.add(city1);
        city1 = new AddCityInfo("washington",38.91, 77.01);
        city_list.add(city1);
        city1 = new AddCityInfo("westPalmBeach",26.71, 80.05);
        city_list.add(city1);
        city1 = new AddCityInfo("wichita",37.69, 97.34);
        city_list.add(city1);
        city1 = new AddCityInfo("winnipeg",49.9, 97.13);
        city_list.add(city1);
        city1 = new AddCityInfo("yuma",32.69, 114.62);
        city_list.add(city1);
        
        //city-code indexing
        Map<String, Integer> map = new HashMap<>();
        map.put("albanyGA",0);
        map.put("albanyNY",1);
        map.put("albuquerque",2);
        map.put("atlanta",3);
        map.put("augusta",4);
        map.put("austin",5);
        map.put("bakersfield",6);
        map.put("baltimore",7);
        map.put("batonRouge",8);
        map.put("beaumont",9);
        map.put("boise",10);
        map.put("boston",11);
        map.put("buffalo",12);
        map.put("calgary",13);
        map.put("charlotte",14);
        map.put("chattanooga",15);
        map.put("chicago",16);
        map.put("cincinnati",17);
        map.put("cleveland",18);
        map.put("coloradoSprings",19);
        map.put("columbus",20);
        map.put("dallas",21);
        map.put("dayton",22);
        map.put("daytonaBeach",23);
        map.put("denver",24);
        map.put("desMoines",25);
        map.put("elPaso",26);
        map.put("eugene",27);
        map.put("europe",28);
        map.put("ftWorth",29);
        map.put("fresno",30);
        map.put("grandJunction",31);
        map.put("greenBay",32);
        map.put("greensboro",33);
        map.put("houston",34);
        map.put("indianapolis",35);
        map.put("jacksonville",36);
        map.put("japan",37);
        map.put("kansasCity",38);
        map.put("keyWest",39);
        map.put("lafayette",40);
        map.put("lakeCity",41);
        map.put("laredo",42);
        map.put("lasVegas",43);
        map.put("lincoln",44);
        map.put("littleRock",45);
        map.put("losAngeles",46);
        map.put("macon",47);
        map.put("medford",48);
        map.put("memphis",49);
        map.put("mexia",50);
        map.put("mexico",51);
        map.put("miami",52);
        map.put("midland",53);
        map.put("milwaukee",54);
        map.put("minneapolis",55);
        map.put("modesto",56);
        map.put("montreal",57);
        map.put("nashville",58);
        map.put("newHaven",59);
        map.put("newOrleans",60);
        map.put("newYork",61);
        map.put("norfolk",62);
        map.put("oakland",63);
        map.put("oklahomaCity",64);
        map.put("omaha",65);
        map.put("orlando",66);
        map.put("ottawa",67);
        map.put("pensacola",68);
        map.put("philadelphia",69);
        map.put("phoenix",70);
        map.put("pittsburgh",71);
        map.put("pointReyes",72);
        map.put("portland",73);
        map.put("providence",74);
        map.put("provo",75);
        map.put("raleigh",76);
        map.put("redding",77);
        map.put("reno",78);
        map.put("richmond",79);
        map.put("rochester",80);
        map.put("sacramento",81);
        map.put("salem",82);
        map.put("salinas",83);
        map.put("saltLakeCity",84);
        map.put("sanAntonio",85);
        map.put("sanDiego",86);
        map.put("sanFrancisco",87);
        map.put("sanJose",88);
        map.put("sanLuisObispo",89);
        map.put("santaFe",90);
        map.put("saultSteMarie",91);
        map.put("savannah",92);
        map.put("seattle",93);
        map.put("stLouis",94);
        map.put("stamford",95);
        map.put("stockton",96);
        map.put("tallahassee",97);
        map.put("tampa",98);
        map.put("thunderBay",99);
        map.put("toledo",100);
        map.put("toronto",101);
        map.put("tucson",102);
        map.put("tulsa",103);
        map.put("uk1",104);
        map.put("uk2",105);
        map.put("vancouver",106);
        map.put("washington",107);
        map.put("westPalmBeach",108);
        map.put("wichita",109);
        map.put("winnipeg",110);
        map.put("yuma",111);
        
        //String search_type = "a-star";
        //String search_type = "greedy";
        //String search_type = "uniform";
        String search_type = args[0];
        String source_city = args[1];
        String dest_city = args[2];
        //String source_city = "japan";
        //String dest_city = "uk1";
        int source_index = map.get(source_city);
        int dest_index = map.get(dest_city);

        /*
        Double estimated_dist;
        estimated_dist = heuristic_dist(city_list.get(source_index).lat,
                city_list.get(source_index).lon,
                city_list.get(dest_index).lat,
                city_list.get(dest_index).lon);
        //System.out.println("h_hat = "+estimated_dist);
        */
        
        //adding routes to the adjacency list
        AddRouteInfo(1,57,226);
        AddRouteInfo(1,11,166);
        AddRouteInfo(1,80,148);
        AddRouteInfo(0,97,120);
        AddRouteInfo(0,47,106);
        AddRouteInfo(2,26,267);
        AddRouteInfo(2,90,61);
        AddRouteInfo(3,47,82);
        AddRouteInfo(3,15,117);
        AddRouteInfo(4,14,161);
        AddRouteInfo(4,92,131);
        AddRouteInfo(5,34,186);
        AddRouteInfo(5,85,79);
        AddRouteInfo(6,46,112);
        AddRouteInfo(6,30,107);
        AddRouteInfo(7,69,102);
        AddRouteInfo(7,107,45);
        AddRouteInfo(8,40,50);
        AddRouteInfo(8,60,80);
        AddRouteInfo(9,34,69);
        AddRouteInfo(9,40,122);
        AddRouteInfo(10,84,349);
        AddRouteInfo(10,73,428);
        AddRouteInfo(11,74,51);
        AddRouteInfo(12,101,105);
        AddRouteInfo(12,80,64);
        AddRouteInfo(12,18,191);
        AddRouteInfo(13,106,605);
        AddRouteInfo(13,110,829);
        AddRouteInfo(14,33,91);
        AddRouteInfo(15,58,129);
        AddRouteInfo(16,54,90);
        AddRouteInfo(16,53,279);
        AddRouteInfo(17,35,110);
        AddRouteInfo(17,22,56);
        AddRouteInfo(18,71,157);
        AddRouteInfo(18,20,142);
        AddRouteInfo(19,24,70);
        AddRouteInfo(19,90,316);
        AddRouteInfo(20,22,72);
        AddRouteInfo(21,24,792);
        AddRouteInfo(21,50,83);
        AddRouteInfo(23,36,92);
        AddRouteInfo(23,66,54);
        AddRouteInfo(24,109,523);
        AddRouteInfo(24,31,246);
        AddRouteInfo(25,65,135);
        AddRouteInfo(25,55,246);
        AddRouteInfo(26,85,580);
        AddRouteInfo(26,102,320);
        AddRouteInfo(27,82,63);
        AddRouteInfo(27,48,165);
        AddRouteInfo(28,69,3939);
        AddRouteInfo(29,64,209);
        AddRouteInfo(30,56,109);
        AddRouteInfo(31,75,220);
        AddRouteInfo(32,55,304);
        AddRouteInfo(32,54,117);
        AddRouteInfo(33,76,74);
        AddRouteInfo(34,50,165);
        AddRouteInfo(35,94,246);
        AddRouteInfo(36,92,140);
        AddRouteInfo(36,41,113);
        AddRouteInfo(37,72,5131);
        AddRouteInfo(37,89,5451);
        AddRouteInfo(38,103,249);
        AddRouteInfo(38,94,256);
        AddRouteInfo(38,109,190);
        AddRouteInfo(39,98,446);
        AddRouteInfo(41,98,169);
        AddRouteInfo(41,97,104);
        AddRouteInfo(42,85,154);
        AddRouteInfo(42,51,741);
        AddRouteInfo(43,46,275);
        AddRouteInfo(43,84,486);
        AddRouteInfo(44,109,277);
        AddRouteInfo(44,65,58);
        AddRouteInfo(45,49,137);
        AddRouteInfo(45,103,276);
        AddRouteInfo(46,86,124);
        AddRouteInfo(46,89,182);
        AddRouteInfo(48,77,150);
        AddRouteInfo(49,58,210);
        AddRouteInfo(52,108,67);
        AddRouteInfo(53,100,82);
        AddRouteInfo(55,110,463);
        AddRouteInfo(56,96,29);
        AddRouteInfo(57,67,132);
        AddRouteInfo(59,74,110);
        AddRouteInfo(59,95,92);
        AddRouteInfo(60,68,268);
        AddRouteInfo(61,69,101);
        AddRouteInfo(62,79,92);
        AddRouteInfo(62,76,174);
        AddRouteInfo(63,87,8);
        AddRouteInfo(63,88,42);
        AddRouteInfo(64,103,105);
        AddRouteInfo(66,108,168);
        AddRouteInfo(66,98,84);
        AddRouteInfo(67,101,269);
        AddRouteInfo(68,97,120);
        AddRouteInfo(69,71,319);
        AddRouteInfo(69,104,3548);
        AddRouteInfo(69,105,3548);
        AddRouteInfo(70,102,117);
        AddRouteInfo(70,111,178);
        AddRouteInfo(72,77,215);
        AddRouteInfo(72,81,115);
        AddRouteInfo(73,93,174);
        AddRouteInfo(73,82,47);
        AddRouteInfo(78,84,520);
        AddRouteInfo(78,81,133);
        AddRouteInfo(79,107,105);
        AddRouteInfo(81,87,95);
        AddRouteInfo(81,96,51);
        AddRouteInfo(83,88,31);
        AddRouteInfo(83,89,137);
        AddRouteInfo(86,111,172);
        AddRouteInfo(91,99,442);
        AddRouteInfo(91,101,436);
        AddRouteInfo(93,106,115);
        AddRouteInfo(99,110,440);
        
        if(search_type.equalsIgnoreCase("astar")){
            System.out.println("A-STAR SEARCH RESULT:");
            a_star(source_index,dest_index);
        }
        else if(search_type.equalsIgnoreCase("greedy")){
            System.out.println("GREEDY BEST FIRST SEARCH RESULT:");
            greedy_best_first(source_index,dest_index);
        }
        else if(search_type.equalsIgnoreCase("uniform")){
            System.out.println("UNIFORM COST SEARCH RESULT:");
            uniform_cost_search(source_index,dest_index);
        }
    }
    
    //method to implement A-Star search
    static void a_star(int src, int dest){
        int[] traversed = new int[112];
        for(int i=0;i<112;i++)
            traversed[i]=0;
        
        Comparator<Object> q_n = new compare_dist_a_star();
        PriorityQueue<queue_node> priority_queue = new PriorityQueue<>(200, q_n);
        ArrayList<Integer> path = new ArrayList();
        ArrayList<Integer> expanded = new ArrayList();
        
        double g=0;
        double h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(src).lat, SearchUSA.city_list.get(src).lon,
                SearchUSA.city_list.get(dest).lat,SearchUSA.city_list.get(dest).lon);
        path.add(src);
        priority_queue.add(new queue_node(g,h,path));
        
        while(!priority_queue.isEmpty()){
            //pop the minimum cost path
            queue_node temp = priority_queue.poll();
            int last_element = temp.path.get(temp.path.size()-1);
            
            if(last_element==dest){
                //List of expanded cities in this algorithm
                System.out.print("Expanded Cities: ");
                for (Integer expanded1 : expanded) {
                    int city_index = expanded1;
                    System.out.print(city_list.get(city_index).city+", ");
                }
                //Number of cities expanded
                System.out.println("\nNumber of cities expanded: "+expanded.size());
                System.out.print("Cities in final solution path: ");
                for (Integer path1 : temp.path) {
                    int x = path1;
                    System.out.print(city_list.get(x).city+", ");
                }
                //Number of cities in final path
                System.out.println("\nNumber of cities in solution path: "+temp.path.size());
                System.out.println("Total Distance(f) = "+temp.f);
                System.exit(1);
            }
            else{
                traversed[last_element]=1;
                expanded.add(last_element);
                for(int i=0;i<adj_list[last_element].size();i++){
                    if(traversed[adj_list[last_element].get(i).dest]==0){
                        int child_node = adj_list[last_element].get(i).dest;
                        ArrayList<Integer> temp_path = new ArrayList();

                        //push all elements of path in temp_path
                        for (Integer path1 : temp.path) {
                            temp_path.add(path1);
                        }
                        temp_path.add(child_node);

                        //update value of 'g' and 'h'
                        g = temp.g + adj_list[last_element].get(i).dist;
                        h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(child_node).lat, 
                                SearchUSA.city_list.get(child_node).lon,
                                SearchUSA.city_list.get(dest).lat,
                                SearchUSA.city_list.get(dest).lon);

                        priority_queue.add(new queue_node(g,h,temp_path));
                    
                    }//if ends
                }//for loop ends
            }//else ends
            remove_duplicates(priority_queue);
        }//while loop ends
        
    }//method a_star ends
    
    //method to implement Greedy-Best First Search
    static void greedy_best_first(int src, int dest){
        int[] traversed = new int[112];
        for(int i=0;i<112;i++)
            traversed[i]=0;
        
        Comparator<Object> q_n = new compare_dist_greedy();
        PriorityQueue<queue_node> priority_queue = new PriorityQueue<>(200, q_n);
        ArrayList<Integer> path = new ArrayList();
        ArrayList<Integer> expanded = new ArrayList();
        
        double g=0;
        double h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(src).lat, SearchUSA.city_list.get(src).lon,
                SearchUSA.city_list.get(dest).lat,SearchUSA.city_list.get(dest).lon);
        path.add(src);
        priority_queue.add(new queue_node(g,h,path));
        
        while(!priority_queue.isEmpty()){
            //pop the minimum cost path
            queue_node temp = priority_queue.poll();
            int last_element = temp.path.get(temp.path.size()-1);
            
            if(last_element==dest){
                //List of expanded cities in this algorithm
                System.out.print("Expanded cities: ");
                for (Integer expanded1 : expanded){
                    int city_index = expanded1;
                    System.out.print(city_list.get(city_index).city+", ");
                }
                
                System.out.println("\nNumber of cities expanded: "+expanded.size());
                System.out.print("Cities in final solution path: ");
                for (Integer path1 : temp.path) {
                    int x = path1;
                    System.out.print(city_list.get(x).city+", ");
                }
                System.out.println("\nNumber of cities in solution path: "+temp.path.size());
                System.out.println("Total Distance(f) = "+temp.f);
                System.exit(1);
            }
            else{
                traversed[last_element]=1;
                expanded.add(last_element);
                for(int i=0;i<adj_list[last_element].size();i++){
                    if(traversed[adj_list[last_element].get(i).dest]==0){
                        int child_node = adj_list[last_element].get(i).dest;
                        ArrayList<Integer> temp_path = new ArrayList();

                        //push all elements of path in temp_path
                        for (Integer path1 : temp.path) {
                            temp_path.add(path1);
                        }
                        temp_path.add(child_node);

                        //update value of 'g' and 'h'
                        g = temp.g + adj_list[last_element].get(i).dist;
                        h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(child_node).lat, 
                                SearchUSA.city_list.get(child_node).lon,
                                SearchUSA.city_list.get(dest).lat,
                                SearchUSA.city_list.get(dest).lon);

                        priority_queue.add(new queue_node(g,h,temp_path));
                    
                    }//if ends
                }//for loop ends
            }//else ends
            
        }//while loop ends
        
    }//method greedy_best_first ends

    //method to implement Uniform Cost Search
    static void uniform_cost_search(int src, int dest){
        int[] traversed = new int[112];
        for(int i=0;i<112;i++)
            traversed[i]=0;
        
        Comparator<Object> q_n = new compare_dist_uniform();
        PriorityQueue<queue_node> priority_queue = new PriorityQueue<>(200, q_n);
        ArrayList<Integer> path = new ArrayList();
        ArrayList<Integer> expanded = new ArrayList();
        
        double g=0;
        double h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(src).lat, SearchUSA.city_list.get(src).lon,
                SearchUSA.city_list.get(dest).lat,SearchUSA.city_list.get(dest).lon);
        path.add(src);
        priority_queue.add(new queue_node(g,h,path));
        
        while(!priority_queue.isEmpty()){
            //pop the minimum cost path
            queue_node temp = priority_queue.poll();
            int last_element = temp.path.get(temp.path.size()-1);
            
            if(last_element==dest){
                //List of cities expanded in this algorithm
                System.out.print("Expanded Cities: ");
                for (Integer expanded1 : expanded){
                    int city_index = expanded1;
                    System.out.print(city_list.get(city_index).city+", ");
                }
                
                System.out.println("\nNumber of cities expanded: "+expanded.size());
                System.out.print("Cities in final solution path: ");
                for (Integer path1 : temp.path) {
                    int x = path1;
                    System.out.print(city_list.get(x).city+", ");
                }
                System.out.println("\nNumber of cities in solution path: "+temp.path.size());
                System.out.println("Total Distance(f) = "+temp.f);
                System.exit(1);
            }
            else{
                traversed[last_element]=1;
                expanded.add(last_element);
                for(int i=0;i<adj_list[last_element].size();i++){
                    if(traversed[adj_list[last_element].get(i).dest]==0){
                        int child_node = adj_list[last_element].get(i).dest;
                        ArrayList<Integer> temp_path = new ArrayList();

                        //push all elements of path in temp_path
                        for (Integer path1 : temp.path) {
                            temp_path.add(path1);
                        }
                        temp_path.add(child_node);

                        //update value of 'g' and 'h'
                        g = temp.g + adj_list[last_element].get(i).dist;
                        h = SearchUSA.heuristic_dist(SearchUSA.city_list.get(child_node).lat, 
                                SearchUSA.city_list.get(child_node).lon,
                                SearchUSA.city_list.get(dest).lat,
                                SearchUSA.city_list.get(dest).lon);

                        priority_queue.add(new queue_node(g,h,temp_path));
                    
                    }//if ends
                }//for loop ends
            }//else ends
            remove_duplicates(priority_queue);
        }//while loop ends
        
    }//method uniform_cost_search ends
    
    //remove duplicate costlier paths
    static void remove_duplicates(PriorityQueue prio_q){
        Comparator<Object> q_n = new compare_dist_a_star();
        PriorityQueue<queue_node> temp_q = new PriorityQueue<>(200, q_n);
        int[] visited = new int[112];
        for(int i=0;i<112;i++)
            visited[i]=0;
        while(!prio_q.isEmpty()){
            queue_node pq = (queue_node)prio_q.poll();
            if(visited[pq.path.get(pq.path.size()-1)]==0){
                temp_q.add(pq);
                visited[pq.path.get(pq.path.size()-1)]=1;
            }
            
        }
        while(!temp_q.isEmpty()){
            queue_node a = temp_q.poll();
            prio_q.add(a);
        }
        
        // How to return prio_q???????
    }
    
    // method to calculate heuristic distance
    static double heuristic_dist(Double lat1,Double lon1,Double lat2,Double lon2){
        Double h_hat;
        h_hat = Math.sqrt(Math.pow((69.5*(lat1-lat2)), 2)+
                Math.pow(69.5*Math.cos((lat1+lat2)*3.14159/360)*(lon1-lon2), 2));
        return h_hat;
    }
    
    //method to add a route information
    static void AddRouteInfo(int s, int d, double dist){
        AddRoute add_route = new AddRoute(d,dist);
        if(adj_list[s]==null){
            adj_list[s] = new LinkedList();
        }
        adj_list[s].add(add_route);
        add_route = new AddRoute(s,dist);
        if(adj_list[d]==null){
            adj_list[d] = new LinkedList();
        }
        adj_list[d].add(add_route);
    }
    
}  
