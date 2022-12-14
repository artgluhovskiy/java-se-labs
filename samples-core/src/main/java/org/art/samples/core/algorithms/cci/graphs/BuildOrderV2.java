package main.java.org.art.samples.core.algorithms.cci.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Build Order - v2" solution from "Cracking the Coding Interview".
 * Topological sort algorithm.
 */
public class BuildOrderV2 {

    public Deque<ProjectV2> buildOrder(String[] projects, String[][] dependencies) {
        ProjectGraphV2 graph = buildProjectGraph(projects, dependencies);
        return buildOrder(graph.getProjects());
    }

    private ProjectGraphV2 buildProjectGraph(String[] projects, String[][] dependencies) {
        ProjectGraphV2 graph = new ProjectGraphV2();
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

    private Deque<ProjectV2> buildOrder(List<ProjectV2> projects) {
        Deque<ProjectV2> order = new LinkedList<>();
        for (ProjectV2 project : projects) {
            if (project.getState() == ProjectState.BLANK) {
                dfs(project, order);
            }
        }
        return order;
    }

    private void dfs(ProjectV2 project, Deque<ProjectV2> order) {
        if (project.getState() == ProjectState.PARTIAL) {
            throw new IllegalStateException("Cycle in the project graph detected. Cannot build the project");
        }
        if (project.getState() == ProjectState.BLANK) {
            project.setState(ProjectState.PARTIAL);
            List<ProjectV2> dependentProjects = project.getDependentProjects();
            for (ProjectV2 depProject : dependentProjects) {
                if (depProject.getState() == ProjectState.BLANK) {
                    dfs(depProject, order);
                }
            }
            project.setState(ProjectState.COMPLETED);
            order.push(project);
        }
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
        Deque<ProjectV2> builtProjects = buildOrder(projects, deps);
        List<String> builtProjectNames = convertStackToList(builtProjects);
        List<String> expectedBuiltProjectNames = List.of("f", "e", "b", "a", "d", "c");
        assertEquals(expectedBuiltProjectNames, builtProjectNames);
    }

    private List<String> convertStackToList(Deque<ProjectV2> projects) {
        List<String> builtProjectList = new ArrayList<>();
        while (!projects.isEmpty()) {
            builtProjectList.add(projects.pop().getName());
        }
        return builtProjectList;
    }
}

class ProjectGraphV2 {

    private final List<ProjectV2> projects = new ArrayList<>();

    private final Map<String, ProjectV2> projectMap = new HashMap<>();

    public ProjectV2 createProject(String name) {
        if (!projectMap.containsKey(name)) {
            ProjectV2 project = new ProjectV2(name);
            projects.add(project);
            projectMap.put(name, project);
            return project;
        } else {
            return projectMap.get(name);
        }
    }

    public ProjectV2 getProject(String name) {
        if (!projectMap.containsKey(name)) {
            throw new IllegalArgumentException("Unknown project name");
        } else {
            return projectMap.get(name);
        }
    }

    public void addProjectDependency(String fromProjectName, String toProjectName) {
        ProjectV2 fromProject = getProject(fromProjectName);
        ProjectV2 toProject = getProject(toProjectName);
        fromProject.addDependentProject(toProject);
    }

    public List<ProjectV2> getProjects() {
        return projects;
    }
}

class ProjectV2 {

    private final String name;

    private int dependencies;

    private final List<ProjectV2> dependentProjects = new ArrayList<>();

    private final Map<String, ProjectV2> dependentProjectMap = new HashMap<>();

    private ProjectState state = ProjectState.BLANK;

    public ProjectV2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addDependentProject(ProjectV2 project) {
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

    public List<ProjectV2> getDependentProjects() {
        return dependentProjects;
    }

    public List<ProjectV2> getBlankDependencies() {
        return dependentProjects.stream().filter(dep -> dep.getState() == ProjectState.BLANK).collect(Collectors.toList());
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }
}

enum ProjectState {
    BLANK, PARTIAL, COMPLETED
}

