public class Planet {
    public static double G = 6.67e-11 ;

    public double mass ;
    public double xxPos ;
    public double yyPos ;
    public double xxVel ;
    public double yyVel ;
    public String imgFileName ;

    //Instance Planet
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP ;
        yyPos = yP ;
        xxVel = xV ;
        yyVel = yV ;
        mass = m ;
        imgFileName = img ;
    }
    //copyInstance Planet
    public Planet(Planet p){
        this.xxPos = p.xxPos ;
        this.yyPos = p.yyPos ;
        this.xxVel = p.xxVel ;
        this.yyVel = p.yyVel ;
        this.mass = p.mass ;
        this.imgFileName = p.imgFileName ;
    }
    public double calcDistance (Planet p){
        double dx = this.xxPos - p.xxPos ;
        double dy = this.yyPos - p.yyPos ;
        return Math.sqrt(dx*dx + dy*dy) ;
    }
    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p)) ;
    }
    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p) ;
    }
    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p) ;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double Fx = 0 ;
        for(Planet p : allPlanets){
            if (p != this){
                Fx += this.calcForceExertedByX(p);
            }
        }
        return Fx ;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double Fy = 0 ;
        for(Planet p : allPlanets){
            if (p != this){
                Fy += this.calcForceExertedByY(p) ;
            }
        }
        return Fy ;
    }
    public void update(double dt, double fX, double fY){
        double ax = fX / this.mass ;
        double ay = fY / this.mass ;
        this.xxVel += ax * dt ;
        this.yyVel += ay * dt ;
        this.xxPos += this.xxVel * dt ;
        this.yyPos += this.yyVel * dt ;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }


}
