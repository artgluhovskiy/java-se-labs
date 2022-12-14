package main.java.org.art.samples.core.algorithms.cci.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Build Order - v1" solution from "Cracking the Coding Interview".
 */
public class BuildOrderV1 {

    public ProjectV1[] buildOrder(String[] projects, String[][] dependencies) {
        ProjectGraphV1 graph = buildProjectGraph(projects, dependencies);
        return buildOrder(graph.getProjects());
    }

    private ProjectGraphV1 buildProjectGraph(String[] projects, String[][] dependencies) {
        ProjectGraphV1 graph = new ProjectGraphV1();
        for (String project : projects) {
            graph.createProject(project);
        }
        for (String[] dependency : dependencies) {
            String first = dependency[0];
            String second = dependency[1];
            graph.addProjectDependency(first, second);
        }
        return graph;
    }

    private ProjectV1[] buildOrder(List<ProjectV1> projects) {
        ProjectV1[] order = new ProjectV1[projects.size()];
        int orderOffset = findNonDependentProjects(order, projects, 0);
        int toBeProcessed = 0;
        while (toBeProcessed < order.length) {
            ProjectV1 current = order[toBeProcessed];
            if (current == null) {
                throw new IllegalStateException("Cycle in the project graph detected. Cannot build the project");
            }
            List<ProjectV1> currentDepProjects = current.getDependentProjects();
            for (ProjectV1 depProject : currentDepProjects) {
                depProject.decDependencies();
            }
            orderOffset = findNonDependentProjects(order, currentDepProjects, orderOffset);
            toBeProcessed++;
        }
        return order;
    }

    private int findNonDependentProjects(ProjectV1[] order, List<ProjectV1> projects, int offset) {
        int newOffset = offset;
        for (ProjectV1 project : projects) {
            if (project.getDependencies() == 0) {
                order[newOffset] = project;
                newOffset++;
            }
        }
        return newOffset;
    }

    @Test
    void test0() {
        String[] projects = {"a", "b", "c", "d", "e", "f"};
        String[][] deps = {
            {"a", "d"},
            {"f", "b"},
            {"b", "d"},
            {"f", "a"},
            {"d", "c"}
        };
        ProjectV1[] builtProjects = buildOrder(projects, deps);
        List<String> builtProjectNames = Arrays.stream(builtProjects).map(ProjectV1::getName).collect(Collectors.toList());
        List<String> expectedBuiltProjectNames = List.of("e", "f", "b", "a", "d", "c");
        assertEquals(expectedBuiltProjectNames, builtProjectNames);
    }

}

class ProjectGraphV1 {

    private final List<ProjectV1> projects = new ArrayList<>();

    private final Map<String, ProjectV1> projectMap = new HashMap<>();

    public ProjectV1 createProject(String name) {
        if (!projectMap.containsKey(name)) {
            ProjectV1 project = new ProjectV1(name);
            projects.add(project);
            projectMap.put(name, project);
            return project;
        } else {
            return projectMap.get(name);
        }
    }

    public ProjectV1 getProject(String name) {
        if (!projectMap.containsKey(name)) {
            throw new IllegalArgumentException("Unknown project name");
        } else {
            return projectMap.get(name);
        }
    }

    public void addProjectDependency(String fromProjectName, String toProjectName) {
        ProjectV1 fromProject = getProject(fromProjectName);
        ProjectV1 toProject = getProject(toProjectName);
        fromProject.addDependentProject(toProject);
    }

    public List<ProjectV1> getProjects() {
        return projects;
    }
}

class ProjectV1 {

    private final String name;

    private int dependencies;

    private final List<ProjectV1> dependentProjects = new ArrayList<>();

    private final Map<String, ProjectV1> dependentProjectMap = new HashMap<>();

    public ProjectV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addDependentProject(ProjectV1 project) {
        if (!dependentProjectMap.containsKey(project.getName())) {
            this.dependentProjects.add(project);
            this.dependentProjectMap.put(project.getName(), project);
            project.incDependencies();
        }
    }

    public void incDependencies() {
        dependencies++;
    }

    public void decDependencies() {
        dependencies--;
    }

    public int getDependencies() {
        return dependencies;
    }

    public List<ProjectV1> getDependentProjects() {
        return dependentProjects;
    }
}

