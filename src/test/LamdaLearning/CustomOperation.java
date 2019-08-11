package test.LamdaLearning;

@FunctionalInterface
public interface CustomOperation {
    int operate(int[] array);


    default int addThree(int a) {
        return a + 3;
    }
}
