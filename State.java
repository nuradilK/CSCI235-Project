import java.util.List;
public abstract class State{
    public boolean isOver, isVisible;
    public abstract void step(Entity e);
    public void moveTo(Vector2D from, Vector2D to){
        if(from.x > to.x){
            from.x--;
        } else if (from.x < to.x){
            from.x++;
        }
        if(from.y > to.y){
            from.y--;
        } else if (from.y < to.y){
            from.y++;
        }
    }
    public State getRandomState(Entity e){
        List<State> stateList = List.of(new Rest(), new ZigZag(), new GotoXY(), new Closest(e));
        int which = (new Common()).randomInt(0, 3);
        while((stateList.get(which).getClass().getName() == e.state.getClass().getName()) || (e.getClass().getName() == "Academician" && which == 3)){
            which = (new Common()).randomInt(0, 3);
        }
        return stateList.get(which);
    }
}

class Stationary extends State{
    public Stationary(){
        isOver = false;
        isVisible = true;
    }
    public void step(Entity e){
        String name = e.getClass().getName();
        if(name == "Quiz" || name == "Lab" || name == "Homework"){
            Assessment assessment = (Assessment) e;
            for(Student i: e.common.students){
                if(assessment.inRange(assessment.position, i.position)){
                    isOver = true;
                    i.grade += assessment.points;
                    break;
                }
            }
            if(isOver == true){
                e.state = new Invisible();
            }
        } 
    }
}
class Invisible extends State{
    public Invisible(){
        isOver = isVisible = false;
    }
    public void step(Entity e){
    }
}
class Rest extends State{
    private int timer = 0;
    private final int duration = 100;
    public Rest(){
        isOver = false;
        isVisible = true;
        timer = 0;
    }
    public void step(Entity e){
        if(isOver == true){
            e.state = getRandomState(e);
            isOver = false;
        } else{
            timer++;
            if(timer == duration)
                isOver = true;
        }
    }
}
class ZigZag extends State{
    private Vector2D direction;
    private final int duration = 100;
    private int timer = 0;
    private Vector2D getRandomDirection(){
        int whichDirection = (new Common()).randomInt(0, 7);
        List<Vector2D> directionList = List.of(new Vector2D(1, 0), new Vector2D(1, 1), new Vector2D(0, 1), 
            new Vector2D(-1, 1), new Vector2D(-1, 0), new Vector2D(-1, -1), new Vector2D(0, -1), new Vector2D(1, -1));
        return directionList.get(whichDirection);
    }
    public ZigZag(){
        isOver = false;
        isVisible = true;
        direction = getRandomDirection();
        timer = 0;
    }
    private boolean onBoard(Vector2D position){
        return (position.x == -1) || (position.y == -1) || (position.x == 801) || (position.y == 401);
    }
    private void changeDirection(Entity e, Vector2D direction){
        if(e.position.x == -1 || e.position.x == 801)
            direction.x *= -1;
        if(e.position.y == -1 || e.position.y == 401)
            direction.y *= -1;
    }
    public void step(Entity e){
        if(isOver == true){
            e.state = getRandomState(e);
            isOver = false;
        } else{
            timer++;
            if(timer == duration){
                isOver = true;
            } else{
                if(onBoard(e.position))
                    changeDirection(e, direction);
                e.position = e.position.plus(direction);
                if(timer % 10 == 0 && e.getClass().getName() == "Academician"){
                    Academician academician = (Academician) e;
                    e.common.assessments.add(academician.createAssessment());
                }
            }
        }
    }
}
class GotoXY extends State{
    public Vector2D location;
    private int timer = 0;
    public GotoXY(){
        isOver = false;
        isVisible = true;
        location = new Vector2D((new Common()).randomInt(0, 800), (new Common()).randomInt(0, 400));        
    }
    public GotoXY(Vector2D position){
        isOver = false;
        isVisible = true;
        location = position;        
    }
    public void step(Entity e){
        if(isOver == true){
            e.state = getRandomState(e);
            isOver = false;
        } else{
            timer++;
            if(e.position.equals(location)){
                isOver = true;
            } else{
                moveTo(e.position, location); 
                if(timer % 10 == 0 && e.getClass().getName() == "Academician"){
                    Academician academician = (Academician) e;
                    e.common.assessments.add(academician.createAssessment());
                }
            }
        }
    }
    public Vector2D getLocation(){
        return location;
    }
}
class Closest extends State{
    private Assessment closest;
    private Assessment getClosestAssessment(Entity e){
        double min = 1e9;
        Assessment tmp = null;
        for(Assessment i: e.common.assessments){
            if(min > e.position.distanceTo(i.position)){
                min = e.position.distanceTo(i.position);
                tmp = i; 
            }
        }
        return tmp;
    }
    public Closest(){
        isOver = false;
        isVisible = true;
        closest = null;
    }
    public Closest(Entity e){
        isOver = false;
        isVisible = true;
        closest = getClosestAssessment(e);
    }
    public void step(Entity e){
        if(isOver == true || closest == null){
            e.state = getRandomState(e);
            isOver = false;
        } else{
            if(e.position.equals(closest.position)){
                isOver = true;
            } else{
                moveTo(e.position, closest.position); 
            } 
        }
    }
}
