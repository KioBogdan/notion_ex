import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';
import { EmailIcon, EmailShareButton, FacebookIcon, FacebookShareButton, RedditIcon, RedditShareButton, TwitterIcon, TwitterShareButton } from 'react-share';
import { js2xml } from 'xml-js';

const ProjectActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [projects, setProjects] = useState(null);
    const shareUrl = 'https://localhost:3000'; // URL to be shared
    const title = 'Still developping this site, but check it out!'; // Title of the shared content
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
    const [project, setProject] = useState({
        task: "",
        projectName: "",
        status: "",
        dateStart: "",
        dateFinish: "",
    });


    const [file, setFile] = useState(); // setting file to import
    const [array, setArray] = useState([]);

    const fileReader = new FileReader();

    const scrollUp = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        })
    }

    const handleOnChange = (e) => {
        setFile(e.target.files[0]);
    };

    const csvFileToArray = string => {
        const csvHeader = string.slice(0, string.indexOf("\n")).split(";");
        const csvRows = string.slice(string.indexOf("\n")+1).split("\n");

        const projectArray = csvRows.map(i => {
            const values = i.split(";");
            const obj = csvHeader.reduce((object, header, index) => {
                object[header] = values[index];
                return object;
            }, {});

            return obj;
        });

        // const financialArray = csvRows.map(i => {
        //     const values = i.split(";");

        //     const financialObj = {
        //         expense: values[1],
        //         amount: values[2],
        //         category: values[3],
        //         date: values[4],
        //     };

        //     console.log(financialObj);
        //     createFinancialParam(financialObj);
        //     return financialObj;

        // });
            
        console.log(projectArray);
        
        for(let i=0; i<= projectArray.length-2; i++) {
                // console.log(financialArray[i].expense);
                // updateProject("task", projectArray[i].task);
                // updateProject("projectName", projectArray[i].projectName);
                // updateProject("status", projectArray[i].status);
                // updateProject("dateStart", projectArray[i].dateStart);
                // updateProject("dateFinish", projectArray[i].dateFinish);

                createProjectParam(projectArray[i]);
        }
        //         console.log(financialArray[i]);
        //         // financial.expense = financialArray[i].expense;
        //         // financial.amount = financialArray[i].amount;
        //         // financial.category = financialArray[i].category;
        //         // financial.date = financialArray[i].date;
        //         //setFinancial(financialArray[i]); //= financialArray[i];
        //         //console.log(financialItem);
        //         //setFinancial(financialArray[i]);
        //         createFinancialParam(financial);
    };

    const handleOnSubmit = (e) => {
        e.preventDefault();

        if(file) {
            fileReader.onload = function (event) {
                const csvOutput = event.target.result;
                csvFileToArray(csvOutput);
            };

            fileReader.readAsText(file);

            //console.log(fileReader);
        }
    };

    const headerKeys = Object.keys(Object.assign({}, ...array));

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

    function createProjectParam(param) {
        fetch("api/project", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(param),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setProject(data);
            console.log(data);
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

                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='projectName'>
                    <Form.Label className='fs-5'>Project Name</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateProject("projectName", e.target.value)} 
                            value={project.projectName}
                            placeholder="Enter project name"/>
                </Form.Group>
                </Col>

                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='status'>
                    <Form.Label className='fs-5'>Project status</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateProject("status", e.target.value)} 
                            value={project.status} 
                            placeholder="Status of the project:"/>
                </Form.Group>
                </Col>

                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='dateStart'>
                    <Form.Label className='fs-5'>Starting Date:</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateProject("dateStart", e.target.value)} 
                            value={project.dateStart}
                            placeholder="Enter starting date"/>
                </Form.Group>
                </Col>

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

            <Row>
                <Col>
                    <Button onClick={() => createProject()}> Add a project task </Button>
                </Col>
            </Row>

            <Row>
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
            </Row>
        </Container>
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
                <Col md="8" lg="6">
                    <Form className="mb-3" controlId='expense'>
                        <input 
                            type={"file"}
                            id={"csvFileInput"}
                            accept={".csv"}
                            onChange={handleOnChange}
                        />

                        <button 
                            onClick={(e) => {
                                handleOnSubmit(e);
                            }}
                        >
                            IMPORT CSV 
                        </button>
                    </Form>
                </Col>
            </Row>
        </Container>

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

            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex gap-3 flex-md-row justify-content-between'>
                    <FacebookShareButton url={shareUrl} quote={title}>
                        <FacebookIcon logoFillColor="blue" />
                    </FacebookShareButton>

                    <TwitterShareButton url={shareUrl} title={title}>
                        <TwitterIcon logoFillColor="green" />
                    </TwitterShareButton>

                    <EmailShareButton url={shareUrl} title={title}>
                        <EmailIcon logoFillColor="mauve" />
                    </EmailShareButton>

                    <RedditShareButton url={shareUrl} title={title}>
                        <RedditIcon logoFillColor="orange" />
                    </RedditShareButton>
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