package svv.com.controlers;

interface State {
    /**
     *  Elevator should go up or down depending on current state or
     *  throw new IllegalStateException
     *
     *  @throws IllegalStateException if the current state is wrong
     * */
    void moving();

    /**
     *  Elevator should stay at floor
     *
     *  @throws IllegalStateException if the current state is wrong
     * */
    void atFloor();
}
