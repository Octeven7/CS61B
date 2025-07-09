public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename) ;
        int firstItemInFile = in.readInt();
        double radius = in.readDouble() ;
        return radius ;
    }
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename) ;
        int PlanetsNumber = in.readInt();
        double radius = in.readDouble() ;
        Planet[] planets = new Planet[PlanetsNumber] ;
        for (int i = 0; i < PlanetsNumber; i++) {
            Planet p = new Planet(in.readDouble(), in.readDouble(),
                                  in.readDouble(), in.readDouble(),
                                  in.readDouble(), in.readString());
            planets[i] = p ;
        }
        return planets ;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]) ;
        double dt = Double.parseDouble(args[1]) ;
        String filename = args[2] ;
        Planet[] planets = readPlanets(filename) ;
        double radius = readRadius(filename) ;

        StdDraw.setScale(0-radius, radius) ;
        StdDraw.clear();
        /**
        StdDraw.picture(0,0,"images/starfield.jpg") ;
        for(Planet p : planets ){
            p.draw();
        }
        StdDraw.show() ;
        */
        StdDraw.enableDoubleBuffering();

        double time = 0 ;
        while(time < T){
            double[] xForces = new double[planets.length] ;
            double[] yForces = new double[planets.length] ;
            for (int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets) ;
                yForces[i] = planets[i].calcNetForceExertedByY(planets) ;
            }
            for(int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]) ;
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg") ;
            for(Planet p : planets ){
                p.draw();
            }
            StdDraw.show() ;
            StdDraw.pause(10) ;
            time += dt ;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
