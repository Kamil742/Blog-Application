// components/BlogList.js
'use client'
import { useEffect, useState } from 'react';
import styles from '../../../ui/dashboard/blogs/blogs.module.css'
import { useRouter } from 'next/navigation';


const BlogList = () => {
  const [blogs, setBlogs] = useState([]);
  const router = useRouter()

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8282/api/blogs/getBlogs');
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
                <div>
                  <h4>Author : {blog.userProfile.firstName} { blog.userProfile.lastName}</h4>
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