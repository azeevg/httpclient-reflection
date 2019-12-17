public aspect Aspect {
    pointcut checkAspect(): call(* CheckAspectJ.check());

    void around() : checkAspect() {
        System.out.println("before");
        proceed();
        System.out.println("after");
    }
}