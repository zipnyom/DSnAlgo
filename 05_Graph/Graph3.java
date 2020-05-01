import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * 
 * 회사에 여러개의 프로젝트가 있는데 어떤 프로젝트들은 특정 프로젝트가 완료되어야만 진행할 수 있는 프로젝트가 있다. 프로젝트와 프로젝트간
 * 의존도가 주어지면 최종 실행 순서를 출력하는 함수를 만드시오.
 * 
 * 
 * 
 */
class Graph3 {

    static class Project {

        String name;
        LinkedList<Project> dependencies;
        boolean marked = false; // 이 부분 빠트림

        Project(String name) {
            this.name = name;
            dependencies = new LinkedList<Project>();
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void addDependeny(Project p) {
            if (dependencies.contains(p) == false) {
                dependencies.add(p);
            }
        }
    }

    static class ProjectManager {

        private HashMap<String, Project> projects;

        ProjectManager(String[] projects, String[][] dependencies) {
            this.projects = new HashMap<String, Project>();
            buildProject(projects);
            setDependencies(dependencies);
        }

        void buildProject(String[] projects) {
            for (String name : projects) {
                this.projects.put(name, new Project(name));
            }
        }

        void setDependencies(String[][] dependencies) {
            for (String[] array : dependencies) {
                // Project p = projects.get(array[1]);
                // if (!p.dependencies.contains(array[0])) {
                // p.dependencies.add(findProject(name));
                // }
                Project before = findProject(array[0]);
                Project after = findProject(array[1]);

                if (after.dependencies.contains(before) == false)
                    after.addDependeny(before);

            }
        }

        void buildOrder() {
            LinkedList<Project> order = new LinkedList<>();
            for (String name : this.projects.keySet()) {
                Project p = findProject(name);
                if (p.marked == false)
                    checkDependencies(p, order);
            }
            for (Project p : order) {
                System.out.print(p.name + " ");
            }
            System.out.println();
        }

        void checkDependencies(Project p, LinkedList<Project> order) {

            if (p.marked)
                return;
            for (Project dependency : p.dependencies) {
                checkDependencies(dependency, order);
            }
            p.marked = true;
            order.add(p);

        }

        Project findProject(String name) {
            return projects.get(name);
        }
    }

    public static void main(String[] args) {

        String[] projects = { "a", "b", "c", "d", "e", "f", "g" };
        String[][] dependencies = { { "f", "a" }, { "f", "b" }, { "f", "c" }, { "b", "a" }, { "c", "a" }, { "a", "e" },
                { "b", "e" }, { "d", "g" } };

        ProjectManager pm = new ProjectManager(projects, dependencies);
        pm.buildOrder();

    }

}