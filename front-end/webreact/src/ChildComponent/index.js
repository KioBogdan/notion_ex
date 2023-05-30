import { Component, useCallback, useRef } from "react";
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { js2xml } from "xml-js";
import { useLocalState } from "../util/useLocalStorage";

// const ChildComponent = () => {
//   const [authors, setAuthors] = useState([]);

//   useEffect(() => {
//     axios
//       .get('api/xml', {
//         headers: {
//           'Content-Type': 'application/xml; charset=utf-8',
//         },
//       })
//       .then(function (response) {
//         setAuthors(response.data);
//       })
//       .catch(function (error) {
//         console.log(error);
//       });
//   }, []);

//   return (
//     <div>
//       Parse XML using ReactJs
//       {authors &&
//         authors.length > 0 &&
//         authors.map((item) => {
//           return <span key={item.id}>{item.amount}</span>;
//         })}
//     </div>
//   );
// };

// export default ChildComponent;


const ChildComponent = () => {

    const [data, setData] = useState([]);
    const [jwt, setJwt] = useLocalState("", "jwt");

    useEffect(() => {
        fetch("api/financial", {
            headers: {
                "Content-Type": "application/json",
                Authorization : `Bearer ${jwt}`,
            }, 
            method: "GET",
        })
        .then(response => {
            setData(response.data);
            console.log(response.data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }, []);

    // const fetchData = () => {
    //     fetch("api/xml", {
    //         headers: {
    //             "Content-Type": "application/json",
    //             Authorization : `Bearer ${jwt}`,
    //         }, 
    //         method: "GET",
    //     })
    //     .then(response => {
    //         setData(response.data);
    //     })
    //     .catch(error => {
    //         console.error('Error fetching data:', error);
    //     });
    // };

    const exportAsXML = () => {
      const xmlData = js2xml(data, { compact: true, ignoreComment: true, spaces: 4 });
      console.log(xmlData);
      
      const blob = new Blob([xmlData], { type: 'application/xml' });
      const url = URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = 'data.xml';
      link.click();
    };
  
    // const handleExportClick = () => {
    //   // Assuming you have your data stored in a variable called 'data'
    //   exportAsXML(data);
    // };
  
    return (
      <div>
        {/* Your component content here */}
        <button onClick={exportAsXML}>Export as XML</button>
      </div>
    );
  };
  
  export default ChildComponent;

    // const showXML = () => {
    //     const xmlH = '<?xml version="1.0" encoding="UTF-8"?>\n';
    
    //     financialService.getAllFinanc().subscribe(
    //       (rez) => {
    //         const json = rez;
    //         const xml = js2xml(json, { compact: true, ignoreComment: true, spaces: 4 });
    //         const xmlFinal = xmlH + xml;
    //         console.log(xmlFinal);
    
    //         const blob = new Blob([xmlFinal], { type: 'application/xml' });
    //         const url = URL.createObjectURL(blob);
    //         const link = document.createElement('a');
    //         link.href = url;
    //         link.download = 'output.xml'; // Set the file name with .xml extension
    //         link.click();
    //       },
    //       (_error) => {}
    //     );
    //   };
    
    //   useEffect(() => {
    //     showXML();
    //   }, []);
    
    //   return <div>Your component content here</div>;