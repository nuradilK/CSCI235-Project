public abstract class AssessmentFactory{
    public Common common;
    public AssessmentFactory(Common common){
        this.common = common;
    }
    public abstract Assessment createAssessment(Vector2D position);
    protected Vector2D getRandomLocation(Vector2D position){
        int x = position.x + common.randomInt(0, 50);
        int y = position.y + common.randomInt(0, 50);
        x = (common.randomInt(0, 1) == 1) ? x * (-1) : x;
        y = (common.randomInt(0, 1) == 1) ? y * (-1) : y;
        return new Vector2D(x, y);
    }
}
class LabFactory extends AssessmentFactory{
    public LabFactory(Common common){
        super(common);
    }
    public Lab createAssessment(Vector2D position){
       return new Lab(getRandomLocation(position), common.randomInt(2, 4), common); 
    } 
}
class QuizFactory extends AssessmentFactory{
    public QuizFactory(Common common){
        super(common);
    }
    public Quiz createAssessment(Vector2D position){
       return new Quiz(getRandomLocation(position), common.randomInt(3, 5), common); 
    }
}
class HomeworkFactory extends AssessmentFactory{
    public HomeworkFactory(Common common){
        super(common);
    }
    public Homework createAssessment(Vector2D position){
       return new Homework(getRandomLocation(position), common.randomInt(1, 3), common); 
    }
}
