import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';

const ProjectActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [projects, setProjects] = useState(null);
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
    const [project, setProject] = useState({
        task: "",
        projectName: "",
        status: "",
        dateStart: "",
        dateFinish: "",
    });

    const scrollUp = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        })
    }

    useEffect(() => {
        fetch("api/project", {
            headers: {
                "Content-Type": "application/json",
                Authorization : `Bearer ${jwt}`,
            }, 
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((projectsData) => {
            setProjects(projectsData);
        });

        window.addEventListener("scroll", () => {
            if(window.scrollY > 50)
                setBackToTopButton(true);
            else 
                setBackToTopButton(false);
        })
    }, [])

    function createProject() {
        fetch("api/project", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(project),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((projectData) => {
            setProject(projectData);
            console.log(projectData);
        })
    }

    function fetchSorted(param) {
        fetch(`api/project/sort/${param}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setProjects(data);
            console.log(data);
        })
    }

    function fetchSearch(param) {
        fetch(`api/project/search/${param}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setProjects(data);
            console.log(data);
        })
    }

    function updateProject(prop, value) {
        const newProject = { ...project };
        newProject[prop] = value;
        setProject(newProject);
    }

    return (
        <Container>
        <Table striped bordered hover variant="dark">
            <thead>
                <tr>
                <th>#</th>
                    <th>Task Name</th>
                    <th>Project Name</th>
                    <th>Status</th>
                    <th>Starting Date</th>
                    <th>Finishing Date</th>
                </tr>
            </thead>
          <tbody>
            {projects ? projects.map((project) => (
                    <tr>
                        <td>{project.id}</td>
                        <td>{project.task}</td>
                        <td>{project.projectName}</td>
                        <td>{project.status}</td>
                        <td>{project.dateStart}</td>
                        <td>{project.dateFinish}</td>
                    </tr> )) : <></>
            }
          </tbody>
        </Table>
          
        <Container className='mt-5'>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='task'>
                    <Form.Label className='fs-5'>Task for the project</Form.Label>
                    <Form.Control type="text"  
                            onChange={(e) => updateProject("task", e.target.value)} 
                            value={project.task}
                            placeholder="Enter task"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='projectName'>
                    <Form.Label className='fs-5'>Project Name</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateProject("projectName", e.target.value)} 
                            value={project.projectName}
                            placeholder="Enter project name"/>
                </Form.Group>
                </Col>
            </Row>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='status'>
                    <Form.Label className='fs-5'>Project status</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateProject("status", e.target.value)} 
                            value={project.status} 
                            placeholder="Status of the project:"/>
                </Form.Group>
                </Col>
            </Row>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='dateStart'>
                    <Form.Label className='fs-5'>Starting Date:</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateProject("dateStart", e.target.value)} 
                            value={project.dateStart}
                            placeholder="Enter starting date"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='username'>
                    <Form.Label className='fs-5'>Finishing Date:</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateProject("dateFinish", e.target.value)} 
                            value={project.dateFinish} 
                            placeholder="Enter finishing date"/>
                </Form.Group>
                </Col>
            </Row>
        </Container>
        
        <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort by:
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item  onClick={() => fetchSorted("task")} >Task</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("projectName")}>Project Name</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("status")}>Status</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("dateStart")}>Starting Date</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("dateFinish")}>Finishing Date</Dropdown.Item>
                </Dropdown.Menu>
        </Dropdown>
          <button onClick={() => createProject()}> Add a project task </button>
          {backToTopButton && (
            <button style={
                {
                    position: "fixed",
                    bottom: "50px",
                    right: "50px",
                    height: "50px",
                    width: "50px",
                    fontSize: "50px",
                }}
                onClick={scrollUp}
            >

            </button>
          )
          }
          <Container>
            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                <Form.Group className="mb-3" controlId='search'>
                    <Form.Label className='fs-5'>Search</Form.Label>
                    <Form.Control type="text" 
                            value={search} 
                            onChange={(event) => setSearch(event.target.value)} 
                            placeholder="search for a task"/>
                </Form.Group>

                <Button id="submit" 
                        type="button" 
                        size='lg'
                        onClick={() => fetchSearch(search)}
                        variant="dark" >
                    Search for
                </Button>

                </Col>
            </Row>
        </Container>
      </Container>
      );

    // return (
    //     <div style={{ margin: "2em" }}>
    //         {projects ? projects.map((project) => (
    //             <div>Project Activity ID: {project.id}</div>)) : <></>}
    //         <button onClick={() => createProject()}> Add a project task </button>
    //     </div>
    // );
};

export default ProjectActivities;