import React, { useState, useRef } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import ReCAPTCHA from 'react-google-recaptcha';
import { useForm } from 'react-hook-form';
const Register = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const captchaRef = useRef(null);
    const [jwt, setJwt] = useLocalState("", "jwt");

    const onSubmit = (data) => {
        // Handle form submission
        setFirstname(data.firstname);
        setLastname(data.lastname);
        setUsername(data.username);
        setEmail(data.email);
        setPassword(data.password);

        sendRegReq();
        console.log(data);
      };

    const handleSubmitG = (e) => {
        e.preventDefault();
        const token = captchaRef.current.getValue();
        console.log(token);
        captchaRef.current.reset();
    }

    function sendRegReq() {
        const reqBody = {
            firstname: firstname,
            lastname: lastname,
            username: username,
            email: email,
            password: password,
        }

        fetch("api/register", {
            headers: {
                "Content-Type": "application/json",
            }, 
            method: "POST",
            body: JSON.stringify(reqBody),
        })
        .then((response) => {
            if(response.status === 200) 
                return Promise.all([response.json(), response.headers]);
            else 
                return Promise.reject("Invalid register attempt");
        })
        .then(([body, headers]) => {
            setJwt(headers.get("authorization"));
            window.location.href = "financial";
        })
        .catch((message) => {
            alert(message);
        })
    }
    // return (
    //     <>
    //     <Container className='mt-5'>
    //         <Row className='justify-content-center'>
    //             <Col md="8" lg="6">
    //             <Form.Group className="mb-3" controlId='firstname' onSubmit={handleSubmitG}>
    //                 <Form.Label className='fs-5'>First Name</Form.Label>
    //                 <Form.Control type="firstname" 
    //                         value={firstname} 
    //                         onChange={(event) => setFirstname(event.target.value)} 
    //                         placeholder="Your first name, e.g. Bill"
    //                         //{...register('firstname', { required: true })}
    //                         />
    //                         {/* //{errors.firstname && <span>First name is Required</span>} */}
    //             </Form.Group>
    //             </Col>
    //         </Row>

    //         <Row className='justify-content-center'>
    //             <Col md="8" lg="6">
    //             <Form.Group className="mb-3" controlId='lastname'>
    //                 <Form.Label className='fs-5'>Last Name</Form.Label>
    //                 <Form.Control type="lastname" 
    //                         value={lastname} 
    //                         onChange={(event) => setLastname(event.target.value)} 
    //                         placeholder="Last name, e.g. Joe"
    //                         //{...register('lastname', { required: true })}
    //                         />
    //                         {/* //{errors.lastname && <span>Last Name is Required</span>} */}
    //             </Form.Group>
    //             </Col>
    //         </Row>

    //         <Row className='justify-content-center'>
    //             <Col md="8" lg="6">
    //             <Form.Group className="mb-3" controlId='username'>
    //                 <Form.Label className='fs-5'>NickName</Form.Label>
    //                 <Form.Control type="username" 
    //                         value={username} 
    //                         onChange={(event) => setUsername(event.target.value)} 
    //                         placeholder="Choose a nickname that suits you"
    //                         //{...register('username', { required: true })}
    //                         />
    //                         {/* //{errors.username && <span>UserName is Required</span>} */}
    //             </Form.Group>
    //             </Col>
    //         </Row>

    //         <Row className='justify-content-center'>
    //             <Col md="8" lg="6">
    //             <Form.Group className="mb-3" controlId='email'>
    //                 <Form.Label className='fs-5'>Email</Form.Label>
    //                 <Form.Control type="email" 
    //                         value={email} 
    //                         onChange={(event) => setEmail(event.target.value)} 
    //                         placeholder="Enter email"
    //                         />
    //                         {/* // {...register('email', { required: true, pattern: /^\S+@\S+$/i })}/>
    //                         // {errors.email && (
    //                         //     <span>
    //                         //         {errors.email.type === 'required'
    //                         //         ? 'This field is required'
    //                         //         : 'Invalid email format'}
    //                         //     </span>
    //                         // )} */}
    //             </Form.Group>
    //             </Col>
    //         </Row>

    //         <Row className='justify-content-center'>
    //             <Col md="8" lg="6">
    //             <Form.Group className="mb-3" controlId='password'>
    //                 <Form.Label className='fs-5'>Password</Form.Label>
    //                 <Form.Control type="password" 
    //                         value={password} 
    //                         onChange={(event) => setPassword(event.target.value)} 
    //                         placeholder="Type in your password"
    //                         //{...register('password', { required: true })}
    //                         />
    //                         {/* {errors.password && <span>Password is required</span>} */}
    //             </Form.Group>
    //             </Col>
    //         </Row>
    //             {/* <Form.Group className="mb-3" controlId='username'>
    //                 <Form.Label className='fs-5'>Email address</Form.Label>
    //                 <Form.Control type="email" 
    //                         value={username} 
    //                         onChange={(event) => setUsername(event.target.value)} placeholder="Enter email"/>
    //             </Form.Group> */}

    //             {/* <Form.Group className="mb-3" controlId='password'>
    //                 <Form.Label className='fs-5'>Password</Form.Label>
    //                 <Form.Control type="password" 
    //                         value={password} 
    //                         onChange={(event) => setPassword(event.target.value)} placeholder="Type in your password"/>
    //             </Form.Group> */}
    //         {/* </Form> */}

    //         <Row className='justify-content-center'>
    //             <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
    //             <Button id="submit" 
    //                     type="button" 
    //                     size='lg'
    //                     onClick={() => sendRegReq()}
    //                     variant="secondary" >
    //                 Register
    //             </Button>
    //             <ReCAPTCHA 
    //             sitekey="6Ld9BuAlAAAAAHn_6kXn0azYF4DuITpZsTQO09mB"
    //             ref={captchaRef}/>
    //             </Col>
    //         </Row>
    //     </Container>
    // </>
    // );

    return (
        <>
          <Container className='mt-5'>
            <Form onSubmit={handleSubmit(onSubmit)}>
              <Form.Group className="mb-3" controlId='firstname'>
                <Form.Label className='fs-5'>First Name</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Your first name, e.g. Bill"
                  {...register('firstname', { required: true })}
                  value={firstname}
                  onChange={(event) => setFirstname(event.target.value)} 
                />
                {errors.firstname && <span>This field is required</span>}
              </Form.Group>
    
              <Form.Group className="mb-3" controlId='lastname'>
                <Form.Label className='fs-5'>Last Name</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Last name, e.g. Joe"
                  {...register('lastname', { required: true })}
                  value={lastname} 
                  onChange={(event) => setLastname(event.target.value)}
                />
                {errors.lastname && <span>This field is required</span>}
              </Form.Group>

              <Form.Group className="mb-3" controlId='username'>
                <Form.Label className='fs-5'>NickName</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Choose a nickname that suits you"
                  {...register('username', { required: true })}
                  value={username} 
                  onChange={(event) => setUsername(event.target.value)}
                />
                {errors.username && <span>This field is required</span>}
              </Form.Group>
    
              <Form.Group className="mb-3" controlId='email'>
                <Form.Label className='fs-5'>Email</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Enter email"
                  {...register('email', { required: true, pattern: /^\S+@\S+$/i })}
                  value={email} 
                  onChange={(event) => setEmail(event.target.value)}
                />
                {errors.email && (
                  <span>
                    {errors.email.type === 'required'
                      ? 'This field is required'
                      : 'Invalid email format'}
                  </span>
                )}
              </Form.Group>
    
              <Form.Group className="mb-3" controlId='password'>
                <Form.Label className='fs-5'>Password</Form.Label>
                <Form.Control
                  type="password"
                  //value={password} 
                  //onChange={(event) => setPassword(event.target.value)} 
                  placeholder="Type in your password"
                  {...register('password', { required: true })}
                  value={password} 
                  onChange={(event) => setPassword(event.target.value)}
                />
                {errors.password && <span>This field is required</span>}
              </Form.Group>
    
            <Row>
                <Button type="submit" size='lg' variant="secondary">
                    Register
                </Button>
                <ReCAPTCHA 
                        sitekey="6Ld9BuAlAAAAAHn_6kXn0azYF4DuITpZsTQO09mB"
                        ref={captchaRef}/>
            </Row>
            </Form>

            {/* <Row className='justify-content-center'>
                 <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                    <Button id="submit" 
                            type="button" 
                            size='lg'
                            onClick={() => sendRegReq()}
                            variant="secondary" >
                        Register
                    </Button>
                    <ReCAPTCHA 
                        sitekey="6Ld9BuAlAAAAAHn_6kXn0azYF4DuITpZsTQO09mB"
                        ref={captchaRef}/>
                 </Col>
             </Row> */}
          </Container>
        </>
      );
};

export default Register;