// 'use client';
// import { useEffect, useState,useParams } from 'react';
// import './viewUserblogs.css'
// import Image from 'next/image';
// import Link from 'next/link';
// import { useRouter } from 'next/navigation';


// export default function ViewAllBlogs() {
//   const router =useRouter()
//   // const { blogId } = useParams()
//   const [blogs, setBlogs] = useState([]);

//   const blogsApi = async () => {
//     try {
//       const response = await fetch('http://localhost:8282/api/blogs/getBlogs/1');
//       const data = await response.json();
//       setBlogs(data);
//       console.log(data);
//     } catch (error) {
//       console.error('Error fetching blogs:', error);
//     }
//   };

//   async function viewBlog (blogId){
//     console.log(blogId,"iddddddddddddddddddd");
//     localStorage.setItem('blogId',blogId)
//     router.push('/user/viewBlogData')
//   }

//   const deleteBlog = async (blogId) => {
//     try {
//       const response = await fetch(`http://localhost:8282/api/blogs/delete/${blogId}`, {
//         method: 'DELETE',
//       });
  
//       if (response.ok) {
//         // Deletion was successful
//         console.log('Blog deleted successfully');
//         blogsApi()
//         // Optionally, you can update the UI or perform any other actions after deletion.
//       } else {
//         // Handle the case when the deletion was not successful
//         console.error('Error deleting blog:', response.statusText);
//       }
//     } catch (error) {
//       console.error('Error deleting blog:', error);
//     }
//   };
  

//   useEffect(() => {
//     blogsApi(); 
//   }, []); 

//   return (
//     <div>
//       <div className='blogcards'>

//         {blogs.map((item) => (
//           <div key={item.blogId} className='data'>
//             <div className='image'>
//              <img src={item.imageUrl} height='100' width='100' alt="Blog Image" />
//             </div>
//             <div className='bloginfo'>
//               <h2>{item.title}</h2>
//               <p>{item.category}</p>
//               <p>{item.content}</p>
//               <p>Date: {item.date}</p>
              
//               <button className='viewButton'
//                     onClick={()=>viewBlog(item.blogId)}
//                     >View</button>
//               <button className='deleteButton'
//                     onClick={()=>deleteBlog(item.blogId)}
//                     >Delete</button>
//             </div>
//           </div>
//         ))}


//       </div>
//     </div>
//   );
// }


// components/BlogList.js
'use client'
import { useEffect, useState } from 'react';
import styles from '../../../ui/dashboard/blogs/blogs.module.css'
import { useRouter } from 'next/navigation';
import { userId } from '../../../constants/constant';


const BlogList = () => {
  const [blogs, setBlogs] = useState([]);
  const router = useRouter()
  const id = userId

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8282/api/blogs/getBlogs/${id}`);
        const data = await response.json();
        setBlogs(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []); // The empty dependency array ensures that the effect runs only once on mount

  const handleClick = (blogId) => {
    // Set the 'blogId' in localStorage
    localStorage.setItem('blogId',blogId);

    // Navigate to the desired page
    router.push('/admin/dashboard/viewblogs');
  };

  return (
    <div className={styles.mainPage}>
    <div className={styles.content}>
      <div className={styles.container}>
        {blogs.map((blog) => (
          <article key={blog.blogId}>
            {/* Pass a function reference to onClick */}
            <a href="#" onClick={() => handleClick(blog.blogId)}>
              <div className={styles.card}>
                <div className={styles.image}>
                  <img src={blog.imageUrl} height={400} width={300} alt={blog.title} /><br /><br />
                </div>
                <div className={styles.text}>
                  <h2>{blog.title}</h2>
                </div>
                <div className={styles.container}>
                <h4> Category : {blog.category.category}</h4>
                  {/* <a href={blog.url} className="read-more" target="_blank" rel="noopener noreferrer">
                    Read More
                  </a> */}
                </div>
              </div>
            </a>
          </article>
        ))}
      </div>
    </div>
    </div>
  );
};

export default BlogList;